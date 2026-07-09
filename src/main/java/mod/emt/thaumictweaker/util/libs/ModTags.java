package mod.emt.thaumictweaker.util.libs;

import com.google.common.collect.ImmutableMap;
import mod.emt.thaumictweaker.config.ConfigOverhaulsTT;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import mod.emt.thaumictweaker.util.helpers.LogHelper;
import mod.emt.thaumictweaker.util.helpers.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.common.entities.construct.EntityOwnedConstruct;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModTags {
    private static ImmutableMap<Fluid, Integer> BRAIN_JAR_XP_FLUIDS;
    private static final Map<Block, Integer> CRUCIBLE_HEAT_SOURCES = new LinkedHashMap<>();
    private static final Map<Class<? extends EntityCreature>, Integer> CHAMPION_MOBS = new HashMap<>();
    private static final Map<BiomeDictionary.Type, Integer> CHAMPION_BIOME_MODIFIER = new HashMap<>();
    private static final Set<ResourceLocation> VIS_EXHAUST_BLACKLIST = new HashSet<>();
    private static final Map<String, Integer> SOUNDING_COLOR_OVERRIDES = new HashMap<>();

    public static ImmutableMap<Fluid, Integer> getBrainJarFluids() {
        return BRAIN_JAR_XP_FLUIDS;
    }

    public static boolean isCrucibleHeatSource(Block block, int meta) {
        if(CRUCIBLE_HEAT_SOURCES.containsKey(block)) {
            int val = CRUCIBLE_HEAT_SOURCES.get(block);
            return val == OreDictionary.WILDCARD_VALUE || val == meta;
        }
        return false;
    }

    public static int getChampionMobChance(Entity entity) {
        if(entity instanceof EntityCreature && !(entity instanceof EntityOwnedConstruct)) {
            if(!entity.isNonBoss() && !ConfigOverhaulsTT.championMobSettings.championBosses) {
                return -1;
            } else if(CHAMPION_MOBS.containsKey(entity.getClass())) {
                return CHAMPION_MOBS.get(entity.getClass());
            } else if(ConfigOverhaulsTT.championMobSettings.globalChampions) {
                return 0;
            }
        }
        return -1;
    }

    public static int getChampionBiomeModifier(Biome biome) {
        int max = 0;
        for(BiomeDictionary.Type type : CHAMPION_BIOME_MODIFIER.keySet()) {
            if(BiomeDictionary.hasType(biome, type)) {
                max = Math.max(max, CHAMPION_BIOME_MODIFIER.get(type));
            }
        }
        return max;
    }

    public static boolean isEntityVisExhaustBlacklisted(Entity entity) {
        EntityEntry entry = EntityRegistry.getEntry(entity.getClass());
        if(entry != null && entry.getRegistryName() != null) {
            return VIS_EXHAUST_BLACKLIST.contains(entry.getRegistryName());
        }
        return false;
    }

    public static int getSoundingColorOverride(int oreId) {
        String oreName = OreDictionary.getOreName(oreId);
        for (String key : SOUNDING_COLOR_OVERRIDES.keySet()) {
            if (oreName.toLowerCase().endsWith(key.toLowerCase())) {
                return SOUNDING_COLOR_OVERRIDES.getOrDefault(key, -1);
            }
        }
        return -1;
    }

    public static void syncConfig() {
        parseBrainJarXpFluids();
        parseCrucibleHeatSources();
        parseChampionMobWhitelist();
        parseChampionBiomeModifiers();
        parseFluxPhageBlacklist();
        parseSoundingColorOverrides();
    }

    private static void parseBrainJarXpFluids() {
        ImmutableMap.Builder<Fluid, Integer> builder = ImmutableMap.builder();
        Pattern pattern = Pattern.compile("^(.+)=(\\d+)$");
        for(String str : ConfigTweaksTT.brain_jar.experienceFluids) {
            try {
                Matcher matcher = pattern.matcher(str);
                if (matcher.find()) {
                    Fluid fluid = FluidRegistry.getFluid(matcher.group(1));
                    int conversionRate = Integer.parseInt(matcher.group(2));
                    if(fluid != null && conversionRate > 0) {
                        builder.put(fluid, conversionRate);
                    } else {
                        LogHelper.warn("Skipping brain jar experience fluid. No fluid found: " + str);
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                LogHelper.error("Invalid experience fluid configuration string: " + str);
            }
        }
        BRAIN_JAR_XP_FLUIDS = builder.build();
    }

    private static void parseCrucibleHeatSources() {
        CRUCIBLE_HEAT_SOURCES.clear();
        Pattern pattern = Pattern.compile("^(.+?):(.+?):?(\\d*)$");
        for(String string : ConfigTweaksTT.crucible.crucibleHeatSources) {
            try {
                Matcher matcher = pattern.matcher(string);
                if (matcher.find()) {
                    String modId = matcher.group(1);
                    String itemId = matcher.group(2);
                    String metaStr = matcher.group(3);
                    int meta = metaStr != null && !metaStr.isEmpty() ? Integer.parseInt(metaStr) : OreDictionary.WILDCARD_VALUE;
                    Block block = RegistryHelper.getRegisteredBlock(new ResourceLocation(modId, itemId));
                    if (block != Blocks.AIR) {
                        CRUCIBLE_HEAT_SOURCES.put(block, meta);
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                LogHelper.error("Invalid crucible heat source configuration string: " + string);
            }
        }
    }

    private static void parseChampionMobWhitelist() {
        CHAMPION_MOBS.clear();
        Pattern pattern = Pattern.compile("^(.+)=(\\d+)$");
        for(String configStr : ConfigOverhaulsTT.championMobSettings.championWhitelist) {
            try {
                Matcher matcher = pattern.matcher(configStr);
                if(matcher.find()) {
                    ResourceLocation loc = new ResourceLocation(matcher.group(1));
                    EntityEntry entry = RegistryHelper.getRegisteredEntity(loc);
                    if(entry != null) {
                        Class<? extends Entity> clazz = entry.getEntityClass();
                        if(EntityCreature.class.isAssignableFrom(clazz)) {
                            int chance = MathHelper.clamp(Integer.parseInt(matcher.group(2)), 0, 100);
                            CHAMPION_MOBS.put(clazz.asSubclass(EntityCreature.class), chance);
                        }
                    }
                }
            } catch(Exception e) {
                LogHelper.error("Invalid champion mob configuration string: " + configStr);
            }
        }
    }

    private static void parseChampionBiomeModifiers() {
        CHAMPION_BIOME_MODIFIER.clear();
        Pattern pattern = Pattern.compile("(.+)=(\\d+)");
        for(String configStr : ConfigOverhaulsTT.championMobSettings.biomeTypeModifier) {
            try {
                Matcher matcher = pattern.matcher(configStr);
                if (matcher.find()) {
                    String typeStr = matcher.group(1).toUpperCase();
                    for (BiomeDictionary.Type type : BiomeDictionary.Type.getAll()) {
                        if (type.getName().equals(typeStr)) {
                            int max = MathHelper.clamp(Integer.parseInt(matcher.group(2)), 0, 100);
                            CHAMPION_BIOME_MODIFIER.put(type, max);
                            break;
                        }
                    }

                }
            } catch (Exception e) {
                LogHelper.error("Invalid champion spawn biome modifier configuration string: " + configStr);
            }
        }
    }

    private static void parseFluxPhageBlacklist() {
        VIS_EXHAUST_BLACKLIST.clear();
        for(String str : ConfigTweaksTT.flux_phage.blacklistedEntities) {
            ResourceLocation loc = new ResourceLocation(str);
            EntityEntry entry = ForgeRegistries.ENTITIES.getValue(loc);
            if(entry != null && entry.getRegistryName() != null) {
                VIS_EXHAUST_BLACKLIST.add(entry.getRegistryName());
            }
        }
    }

    private static void parseSoundingColorOverrides() {
        SOUNDING_COLOR_OVERRIDES.clear();
        Pattern pattern = Pattern.compile("^(\\w+)=([\\da-fA-F]{6})$");
        for(String str : ConfigTweaksTT.infusion_enchantments.soundingColorOverrides) {
            try {
                Matcher matcher = pattern.matcher(str);
                if(matcher.find()) {
                    SOUNDING_COLOR_OVERRIDES.put(matcher.group(1).toLowerCase(), Integer.parseInt(matcher.group(2), 16));
                }
            } catch (Exception e) {
                LogHelper.warn("Invalid sounding color override string: " + str);
            }
        }
    }

    static {
        syncConfig();
    }
}
