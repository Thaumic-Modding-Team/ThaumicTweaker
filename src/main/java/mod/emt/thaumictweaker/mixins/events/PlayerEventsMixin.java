package mod.emt.thaumictweaker.mixins.events;

import com.llamalad7.mixinextras.sugar.Local;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thaumcraft.api.items.IRechargable;
import thaumcraft.common.lib.events.PlayerEvents;

import java.util.List;

@Mixin(value = PlayerEvents.class, remap = false)
public class PlayerEventsMixin {
    @Redirect(
            method = "handleRunicArmor",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;setAbsorptionAmount(F)V",
                    remap = true
            )
    )
    private static void setRunicShieldingMixin(EntityPlayer player, float amount) {
        RunicShieldingHandler.setRunicShielding(player, amount);
    }

    @Redirect(
            method = "handleRunicArmor",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/EntityPlayer;getAbsorptionAmount()F",
                    remap = true
            )
    )
    private static float getRunicShieldingMixin(EntityPlayer player) {
        return (float) RunicShieldingHandler.getRunicShielding(player);
    }

    @SideOnly(Side.CLIENT)
    @Redirect(
            method = "tooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/List;add(Ljava/lang/Object;)Z",
                    ordinal = 3
            )
    )
    private static <E> boolean enhancedVisTooltipMixin(List<String> instance, E e, @Local(argsOnly = true) ItemTooltipEvent event) {
        String original = (String) e;
        ItemStack stack = event.getItemStack();
        if(ConfigTweaksTT.tooltips.maxVisTooltip && stack.getItem() instanceof IRechargable) {
            int maxCharge = ((IRechargable) stack.getItem()).getMaxCharge(stack, event.getEntityPlayer());
            original += " / " + maxCharge;
        }
        return event.getToolTip().add(original);
    }
}
