package mod.emt.thaumictweaker.mixins.thaumicaugmentation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import thecodex6824.thaumicaugmentation.common.tile.TileRiftMoverOutput;

@Mixin(value = TileRiftMoverOutput.class, remap = false)
public class TileRiftMoverOutputMixin {
    @ModifyConstant(method = "onCasterRightClick", constant = @Constant(intValue = 1, ordinal = 0))
    private int setRiftSizeMixin(int original) {
        return 2;
    }

    @ModifyConstant(method = "onCasterRightClick", constant = @Constant(intValue = 1, ordinal = 1))
    private int setLastSize(int original) {
        return 2;
    }
}
