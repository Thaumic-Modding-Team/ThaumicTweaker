package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.config.ConfigEnhancementsTT;
import mod.emt.thaumictweaker.mixins.client.gui.GuiScreenAccessor;
import mod.emt.thaumictweaker.network.PacketHandlerTT;
import mod.emt.thaumictweaker.network.packets.MessageScanSelf;
import mod.emt.thaumictweaker.network.packets.MessageScanSlot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.lib.events.RenderEventHandler;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

@SideOnly(Side.CLIENT)
public class InventoryScanningHandler {
    private static final int INVENTORY_PLAYER_X = 26;
    private static final int INVENTORY_PLAYER_Y = 8;
    private static final int INVENTORY_PLAYER_WIDTH = 52;
    private static final int INVENTORY_PLAYER_HEIGHT = 70;

    private static Object currentScan;
    private static int ticksHovered;
    private static boolean isHoveringPlayer;
    private static Slot mouseSlot;

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack hoverStack = event.getItemStack();
        if(player != null && !hoverStack.isEmpty()) {
            ItemStack cursorStack = player.inventory.getItemStack();
            if(cursorStack.isEmpty() && hoverStack.getItem() == ItemsTC.thaumometer) {
                event.getToolTip().add(TextFormatting.GOLD + I18n.format("tooltip.thaumictweaker:scanning.info"));
                if (GuiScreen.isShiftKeyDown()) {
                    String[] lines = I18n.format("tooltip.thaumictweaker:scanning.desc").split("\\\\n");
                    for (String line : lines) {
                        event.getToolTip().add(TextFormatting.DARK_AQUA + line);
                    }
                }
            } else if(isHoldingThaumometer(player)) {
                AspectList aspects = ThaumcraftCraftingManager.getObjectTags(hoverStack);
                boolean hasNoAspects = (aspects == null || aspects.size() == 0);
                boolean isScannable = ScanningManager.isThingStillScannable(player, hoverStack);
                TextFormatting color = isScannable ? TextFormatting.GREEN : TextFormatting.RED;
                if (hasNoAspects) {
                    String text = I18n.format("tooltip.thaumictweaker:scanning_available.no_aspects");
                    event.getToolTip().add(TextFormatting.DARK_GRAY + text);
                } else {
                    String text = I18n.format("tooltip.thaumictweaker:scanning_available." + (isScannable ? "true" : "false"));
                    event.getToolTip().add(color + text);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onTooltipPostText(RenderTooltipEvent.PostText event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        if (ConfigEnhancementsTT.inventoryScanning.renderAspects && isHoldingThaumometer(player) && !GuiScreen.isShiftKeyDown()) {
            if (mc.currentScreen instanceof GuiContainer && !ScanningManager.isThingStillScannable(player, event.getStack())) {
                RenderEventHandler.hudHandler.renderAspectsInGui((GuiContainer) mc.currentScreen, player, event.getStack(), 0, event.getX(), event.getY());
            }
        }
    }

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;
        if(event.phase == TickEvent.Phase.END && isHoldingThaumometer(player)) {
            if(currentScan != null && ScanningManager.isThingStillScannable(player, currentScan)) {
                ticksHovered++;
                if (ticksHovered >= 2 && ConfigEnhancementsTT.inventoryScanning.scanningProgressSound) player.world.playSound(player.posX, player.posY, player.posZ, SoundsTC.ticks, SoundCategory.NEUTRAL, 0.2f, 0.45f + player.world.rand.nextFloat() * 0.1f, false);
                if (ticksHovered >= ConfigEnhancementsTT.inventoryScanning.timeToScan) {
                    player.world.playSound(player.posX, player.posY, player.posZ, SoundsTC.scan, SoundCategory.NEUTRAL, 1.0f, 1.0f, false);
                    if (currentScan instanceof EntityPlayer) {
                        PacketHandlerTT.INSTANCE.sendToServer(new MessageScanSelf());
                    } else {
                        PacketHandlerTT.INSTANCE.sendToServer(new MessageScanSlot(mouseSlot.slotNumber));
                    }
                    resetScan();
                }
            } else {
                resetScan();
            }
        }
    }

    @SubscribeEvent
    public static void onDrawScreen(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (event.getGui() instanceof GuiContainer && !(event.getGui() instanceof GuiContainerCreative)) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayer player = mc.player;
            GuiContainer gui = (GuiContainer) event.getGui();
            if(isHoldingThaumometer(player)) {
                Slot newSlot = gui.getSlotUnderMouse();
                boolean newIsHoveringPlayer = isHoveringPlayer((GuiContainer) event.getGui(), event.getMouseX(), event.getMouseY());

                //Rendering Tooltips and aspects
                if(newSlot != null && newSlot.getHasStack() && event.getGui() instanceof GuiScreenAccessor) {
                    ((GuiScreenAccessor) event.getGui()).invokeRenderToolTip(newSlot.getStack(), event.getMouseX(), event.getMouseY());
                } else if(newIsHoveringPlayer && !ScanningManager.isThingStillScannable(player, player)) {
                    renderPlayerAspects(event.getGui(), event.getMouseX(), event.getMouseY());
                }

                updateScannable(player, newSlot, newIsHoveringPlayer);

                if (currentScan != null && ticksHovered < ConfigEnhancementsTT.inventoryScanning.timeToScan && ScanningManager.isThingStillScannable(player, currentScan)) {
                    renderScanningProgress(gui, event.getMouseX(), event.getMouseY());
                }
            } else {
                resetScan();
            }
        }
    }

    private static boolean isHoldingThaumometer(EntityPlayer player) {
        return player != null && !player.inventory.getItemStack().isEmpty() && player.inventory.getItemStack().getItem() == ItemsTC.thaumometer;
    }

    private static boolean isHoveringPlayer(GuiContainer gui, int mouseX, int mouseY) {
        return gui instanceof GuiInventory
                && mouseX >= gui.getGuiLeft() + INVENTORY_PLAYER_X
                && mouseX < gui.getGuiLeft() + INVENTORY_PLAYER_X + INVENTORY_PLAYER_WIDTH
                && mouseY >= gui.getGuiTop() + INVENTORY_PLAYER_Y
                && mouseY < gui.getGuiTop() + INVENTORY_PLAYER_Y + INVENTORY_PLAYER_HEIGHT;
    }

    private static boolean validateSlot(@NotNull EntityPlayer player, Slot slot) {
        return slot != null && slot.getHasStack() && slot.canTakeStack(player) && !(slot instanceof SlotCrafting);
    }

    private static void updateScannable(@NotNull EntityPlayer player, Slot hoveredSlot, boolean isMouseOverPlayer) {
        if(isMouseOverPlayer) {
            if (!isHoveringPlayer)
                resetScan();
            isHoveringPlayer = true;
            currentScan = player;
        } else if(validateSlot(player, hoveredSlot)) {
            if(hoveredSlot != mouseSlot)
                resetScan();
            mouseSlot = hoveredSlot;
            currentScan = hoveredSlot.getStack();
        } else {
            resetScan();
        }
    }

    private static void resetScan() {
        ticksHovered = 0;
        currentScan = null;
        mouseSlot = null;
        isHoveringPlayer = false;
    }

    private static void renderPlayerAspects(GuiScreen gui, int mouseX, int mouseY) {
        GlStateManager.pushMatrix();
        GlStateManager.color(1f, 1f, 1f, 1f);
        GlStateManager.disableLighting();
        int x = mouseX + 17;
        int y = mouseY + 7 - 33;
        EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();
        AspectList aspectList = AspectHelper.getEntityAspects(entityPlayer);
        if (aspectList != null && aspectList.size() > 0) {
            GlStateManager.disableDepth();
            Aspect[] sortedAspects = aspectList.getAspectsSortedByAmount();
            for (Aspect aspect : sortedAspects) {
                if (aspect != null) {
                    UtilsFX.drawTag(x, y, aspect, aspectList.getAmount(aspect), 0, gui.zLevel);
                    x += 18;
                }
            }
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
        }
        GlStateManager.popMatrix();
    }

    private static void renderScanningProgress(GuiScreen gui, int mouseX, int mouseY) {
        String text = TextFormatting.GOLD + I18n.format("tooltip.thaumictweaker:scanning");
        float progress = (ticksHovered / (float) ConfigEnhancementsTT.inventoryScanning.timeToScan);
        if (progress >= 0.75f) {
            text += "...";
        } else if (progress >= 0.5f) {
            text += "..";
        } else if (progress >= 0.25f) {
            text += ".";
        }
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        float oldZLevel = gui.zLevel;
        gui.zLevel = 300;
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(text, mouseX, mouseY - 30, 0xFFFFFFFF);
        gui.zLevel = oldZLevel;
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableRescaleNormal();
    }

}
