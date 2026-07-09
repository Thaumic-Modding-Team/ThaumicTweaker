package mod.emt.thaumictweaker.events;

import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.util.libs.ModTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.FluidTankProperties;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import thaumcraft.common.tiles.devices.TileJarBrain;

import java.util.*;

public class BrainJarCapabilityHandler {
    public static final ResourceLocation XP_FLUID_CAP = new ResourceLocation(ThaumicTweaker.MOD_ID, "xp_fluid");

    @SubscribeEvent
    public void onAttachCapability(AttachCapabilitiesEvent<TileEntity> event) {
        if(event.getObject() instanceof TileJarBrain) {
            event.addCapability(XP_FLUID_CAP, new ExperienceFluidCapabilityProvider((TileJarBrain) event.getObject()));
        }
    }

    public static class ExperienceFluidCapabilityProvider implements IFluidHandler, ICapabilityProvider {
        private final TileJarBrain brainJar;

        public ExperienceFluidCapabilityProvider(TileJarBrain brainJar) {
            this.brainJar = brainJar;
        }

        @Override
        public IFluidTankProperties[] getTankProperties() {
            if(this.brainJar.xp > 0 && !ModTags.getBrainJarFluids().isEmpty()) {
                List<IFluidTankProperties> props = new ArrayList<>();
                ModTags.getBrainJarFluids().forEach((fluid, conversionRate) -> {
                    props.add(new FluidTankProperties(new FluidStack(fluid, this.brainJar.xp * conversionRate), this.brainJar.xpMax * conversionRate));
                });
                return props.toArray(new IFluidTankProperties[0]);
            } else {
                return new IFluidTankProperties[] {new FluidTankProperties(null, 1, false, false)};
            }
        }

        @Override
        public int fill(FluidStack resource, boolean doFill) {
            if (resource != null && resource.getFluid() != null) {
                Map<Fluid, Integer> xpFluids = ModTags.getBrainJarFluids();
                if(xpFluids.containsKey(resource.getFluid())) {
                    int conversion = xpFluids.get(resource.getFluid());
                    int filledFluid = Math.min(resource.amount - (resource.amount % conversion), (this.brainJar.xpMax - this.brainJar.xp) * conversion);
                    int filledXp = filledFluid / conversion;
                    if(filledFluid <= 0) {
                        return 0;
                    }

                    if(doFill) {
                        this.brainJar.xp += filledXp;
                        this.brainJar.syncTile(false);
                        this.brainJar.markDirty();
                        return filledFluid;
                    }
                }
            }
            return 0;
        }

        @Override
        public @Nullable FluidStack drain(FluidStack resource, boolean doDrain) {
            if (resource != null && resource.getFluid() != null) {
                Map<Fluid, Integer> xpFluids = ModTags.getBrainJarFluids();
                if(xpFluids.containsKey(resource.getFluid())) {
                    int conversion = xpFluids.get(resource.getFluid());
                    int drainedFluid = Math.min(resource.amount - (resource.amount % conversion), this.brainJar.xp * conversion);
                    int drainedXp = drainedFluid / conversion;
                    if(drainedFluid <= 0) {
                        return null;
                    }

                    FluidStack drained = new FluidStack(resource.getFluid(), drainedFluid);
                    if(doDrain) {
                        this.brainJar.xp -= drainedXp;
                        this.brainJar.syncTile(false);
                        this.brainJar.markDirty();
                    }
                    return drained;
                }
            }
            return null;
        }

        @Override
        public @Nullable FluidStack drain(int maxDrain, boolean doDrain) {
            if(maxDrain > 0) {
                Optional<Fluid> xpFluid = ModTags.getBrainJarFluids().keySet().stream().findFirst();
                if(xpFluid.isPresent()) {
                    return this.drain(new FluidStack(xpFluid.get(), maxDrain), doDrain);
                }
            }
            return null;
        }

        @Override
        public boolean hasCapability(@NotNull Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
        }

        @Override
        public @Nullable <T> T getCapability(@NotNull Capability<T> capability, @Nullable EnumFacing facing) {
            if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
                return CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.cast(this);
            }
            return null;
        }
    }
}
