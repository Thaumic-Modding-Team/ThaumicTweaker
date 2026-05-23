package mod.emt.thaumictweaker.mixins.items;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.util.EnumActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.common.items.tools.ItemHandMirror;

@Mixin(value = ItemHandMirror.class, remap = false)
public class ItemHandMirrorMixin {
    @ModifyReturnValue(method = "onItemUseFirst", at = @At(value = "RETURN", ordinal = 2))
    private EnumActionResult modifyFailActionMixin(EnumActionResult original) {
        return original == EnumActionResult.FAIL ? EnumActionResult.PASS : original;
    }
}
