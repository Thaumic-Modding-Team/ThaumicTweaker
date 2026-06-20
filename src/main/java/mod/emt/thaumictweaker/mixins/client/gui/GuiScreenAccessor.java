package mod.emt.thaumictweaker.mixins.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@SideOnly(Side.CLIENT)
@Mixin(GuiScreen.class)
public interface GuiScreenAccessor {

    @Invoker("renderToolTip")
    void invokeRenderToolTip(ItemStack stack, int x, int y);
}
