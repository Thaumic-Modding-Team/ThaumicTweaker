package mod.emt.thaumictweaker.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import mod.emt.thaumictweaker.ThaumicTweaker;
import mod.emt.thaumictweaker.config.generics.GolemMaterialCategory;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config." + ThaumicTweaker.MOD_ID + ":tweaks")
@Config(
        modid = ThaumicTweaker.MOD_ID,
        name = ThaumicTweaker.MOD_ID + "/" + ThaumicTweaker.MOD_NAME + " - Tweaks"
)
public class ConfigTweaksTT {
    @Config.Name("Apprentice's Ring Tweaks")
    public static ApprenticesRingCategory apprentices_ring = new ApprenticesRingCategory();
    @Config.Name("Crucible Tweaks")
    public static CrucibleCategory crucible = new CrucibleCategory();
    @Config.Name("Crimson Cult Robes Tweaks")
    public static CrimsonCultRobesCategory cult_robes = new CrimsonCultRobesCategory();
    @Config.Name("Curiosity Tweaks")
    public static CuriosityTweaksCategory curiosity_tweaks = new CuriosityTweaksCategory();
    @Config.Name("Equipment Tweaks")
    public static EquipmentTweaksCategory equipment_tweaks = new EquipmentTweaksCategory();
    @Config.Name("Fluid Death Tweaks")
    public static FluidDeathCategory fluid_death = new FluidDeathCategory();
    @Config.Name("Flux Phage Tweaks")
    public static FluxPhageCategory flux_phage = new FluxPhageCategory();
    @Config.Name("Flux Pollution Tweaks")
    public static FluxPollutionCategory flux_pollution = new FluxPollutionCategory();
    @Config.Name("Fortress Armor Tweaks")
    public static FortressArmorCategory fortress_armor = new FortressArmorCategory();
    @Config.Name("Furious Zombie Tweaks")
    public static FuriousZombieCategory furious_zombie = new FuriousZombieCategory();
    @Config.Name("Golem Tweaks")
    public static GolemTweaksCategory golem_tweaks = new GolemTweaksCategory();
    @Config.Name("Infusion Enchantment Tweaks")
    public static InfusionEnchantmentCategory infusion_enchantments = new InfusionEnchantmentCategory();
    @Config.Name("Miscellaneous Tweaks")
    public static MiscTweaksCategory misc_tweaks = new MiscTweaksCategory();
    @Config.Name("Pech Tweaks")
    public static PechCategory pech_tweaks = new PechCategory();
    @Config.Name("Porous Stone Tweaks")
    public static PorousStoneCategory porous_stone = new PorousStoneCategory();
    @Config.Name("Primal Crusher Tweaks")
    public static PrimalCrusherCategory primal_crusher = new PrimalCrusherCategory();
    @Config.Name("Research Table Tweaks")
    public static ResearchTableCategory research_table = new ResearchTableCategory();
    @Config.Name("Thaumonomicon Tweaks")
    public static ThaumonomiconCategory thaumonomicon = new ThaumonomiconCategory();
    @Config.Name("Vis Generator Tweaks")
    public static VisGeneratorCategory vis_generator = new VisGeneratorCategory();
    @Config.Name("Void Thaumaturge Robes Tweaks")
    public static VoidRobesCategory void_robes = new VoidRobesCategory();
    @Config.Name("Void Siphon Tweaks")
    public static VoidSiphonCategory void_siphon = new VoidSiphonCategory();

    public static class ApprenticesRingCategory {
        @Config.RequiresMcRestart
        @Config.Name("Apprentice's Ring Structure Loot")
        @Config.Comment("Allows the Apprentice's Ring to be rarely found in some vanilla structure chests.")
        public boolean enableStructureLoot = false;

        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Apprentice's Ring Vis Discount")
        @Config.Comment("The Vis discount granted by the Apprentice's Ring.")
        public int visDiscount = 5;
    }

    public static class CrimsonCultRobesCategory {
        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Helm Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Crimson Cult Hood.")
        public int visDiscountHelm = 1;

        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Robes Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Crimson Cult Robes.")
        public int visDiscountChest = 1;

        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Leggings Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Crimson Cult Leggings.")
        public int visDiscountLeggings = 1;
    }

    public static class CrucibleCategory {
        @Config.Name("Crucible Heat Sources")
        @Config.Comment
                ({
                        "A list of blocks that can be used as Crucible heat sources.",
                        "Format:",
                        "  modid:blockid",
                        "  modid:blockid:meta",
                        "Example:",
                        "  minecraft:stone",
                        "  minecraft:stone:0"
                })
        public String[] crucibleHeatSources = new String[] {};

        @Config.Name("Disable Item Interaction")
        @Config.Comment("Disables item interaction when right-clicking on a boiling crucible, preventing items from being consumed.")
        public boolean disableItemInteraction = false;
    }

    public static class CuriosityTweaksCategory {
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.Name("Eldritch Guardian Curiosity Chance")
        @Config.Comment("The chance for an Eldritch Guardian to drop an Eldritch Curiosity.")
        public double curioDropEldritchGuardian = 0.0D;

        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.Name("Giant Taint Seed Curiosity Chance")
        @Config.Comment("The chance for a Giant Taint Seed to drop a Twisted Curiosity.")
        public double curioDropGiantTaintSeed = 0.0D;
    }

    public static class FluidDeathCategory {
        @Config.RangeDouble(min = 0, max = 10000)
        @Config.Name("Damage Modifier")
        @Config.Comment
                ({
                        "A modifier that increases the amount of damage done when an entity is in contact with liquid death. A ",
                        "value of 1.0 will deal default damage, a value of 2.0 will deal double damage, and so on."
                })
        public double damageModifier = 1.0;
    }

    public static class FluxPhageCategory {
        @Config.Name("Entity Blacklist")
        @Config.Comment("A list of entities that will not be infected with Flux Phage. Format: minecraft:zombie")
        public String[] blacklistedEntities = new String[] {};
    }

    public static class FluxPollutionCategory {
        @Config.RangeInt(min = 1, max = 10000)
        @Config.Name("Essentia Mirror Instability Decay Rate")
        @Config.Comment("The time, in ticks, between each instability decay operation of the Essentia Mirror. Smaller values will result in less flux.")
        public int essentiaMirrorInstabilityDecay = 100;

        @Config.RangeInt(min = 1, max = 10000)
        @Config.Name("Mirror Instability Decay Rate")
        @Config.Comment("The time, in ticks, between each instability decay operation of the Mirror. Smaller values will result in less flux.")
        public int mirrorInstabilityDecay = 100;
    }

    public static class FortressArmorCategory {
        @Config.Name("Enable Knockback Resistance")
        @Config.Comment("Gives the Fortress Armor knockback resistance. Chest = 0.4, Legs = 0.3, Helm = 0.3")
        public boolean enableKnockbackResistance = false;
    }

    public static class FuriousZombieCategory {
        @Config.Name("Enable Spawning")
        @Config.Comment("Enables natural spawning for Furious Zombies. [default: true]")
        @Config.RequiresMcRestart
        public boolean enableFuriousZombieSpawning = true;

        @Config.Name("Enable Underground Spawning")
        @Config.Comment("Enables underground spawning for Furious Zombies. [default: false]")
        @Config.RequiresMcRestart
        public boolean enableUndergroundSpawning = false;

        @Config.Name("Spawn Weight")
        @Config.Comment("Default spawn weight of Furious Zombies. [default: 5]")
        @Config.RangeInt(min = 0)
        @Config.RequiresMcRestart
        public int spawnWeight = 5;
    }

    public static class GolemTweaksCategory {
        @Config.Name("Material: Greatwood")
        public GolemMaterialCategory matWood = new GolemMaterialCategory(6, 2, 1, "thaumcraft:plank_greatwood");
        @Config.Name("Material: Iron")
        public GolemMaterialCategory matIron = new GolemMaterialCategory(20, 8, 3, "thaumcraft:plate:1");
        @Config.Name("Material: Clay")
        public GolemMaterialCategory matClay = new GolemMaterialCategory(10, 4, 2, "minecraft:hardened_clay");
        @Config.Name("Material: Brass")
        public GolemMaterialCategory matBrass = new GolemMaterialCategory(10, 4, 2, "thaumcraft:plate:0");
        @Config.Name("Material: Thaumium")
        public GolemMaterialCategory matThaumium = new GolemMaterialCategory(24, 10, 4, "thaumcraft:plate:2");
        @Config.Name("Material: Void Metal")
        public GolemMaterialCategory matVoid = new GolemMaterialCategory(20, 6, 4, "thaumcraft:plate:3");
    }

    public static class InfusionEnchantmentCategory {
        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Refining Base Increase")
        @Config.Comment
                ({
                        "The base increase the Refining infusion enchant has when harvesting ores. The calculation is equal",
                        "to: chance = (refiningLevel + baseIncrease) * clusterChance"
                })
        public int refiningBaseIncrease = 1;

        @Config.RangeDouble(min = 0, max = 1.0)
        @Config.Name("Refining Purification Chance")
        @Config.Comment("The chance each level of Refining has to purify ore into its cluster variant.")
        public double refiningClusterChance = 0.125;

        @Config.RangeDouble(min = 0, max = 1.0)
        @Config.Name("Refining Fortune Bonus")
        @Config.Comment("The amount each level of Fortune will increase the chance Refining will purify ore into its cluster variant.")
        public double refiningFortuneBonus = 0;

        @Config.Name("Sounding Color Overrides")
        @Config.Comment
                ({
                        "Add, remove or modify the particle colors whenever a nearby block is detected with the sounding infusion enchant.",
                        "Format:",
                        "  material=hexcolor",
                        "Examples:",
                        "  iron=D8AF93",
                        "  coal=101010"
                })
        public String[] soundingColorOverrides = new String[] {
                "aluminum=EFEFF1",
                "ambrosium=D8DE28",
                "amethyst=AD53DA",
                "anglesite=B16100",
                "apatite=36A1E5",
                "aquamarine=3AECFF",
                "ardite=E5B740",
                "astralStarmetal=003C89",
                "bauxite=EFEFF1",
                "benitoite=00B3A4",
                "blueTopaz=66C6F2",
                "certusQuartz=BADBFF",
                "chargedCertusQuartz=BADBFF",
                "cheese=CCB600",
                "chimerite=BBE0CB",
                "cobalt=2E7CE6",
                "desh=545454",
                "draconium=4B236E",
                "electrotine=0A467A",
                "firestone=DD731E",
                "fluorite=9893E0",
                "galena=5B5B5B",
                "gravitite=D061AE",
                "ilmenite=3A4E70",
                "iridium=BDBED1",
                "lead=5B5B5B", //Thaumcraft does not assign a color for it despite having a cluster -_-
                "malachite=70D3C1",
                "mithril=5CD6FF",
                "moonstone=91ADC7",
                "nickel=9EA59E",
                "osmium=B5CACC",
                "peridot=83D951",
                "phosphorus=D8E0AD",
                "platinum=C6D5F2",
                "pyrite=E8BF65",
                "ruby=D04545",
                "salt=EFE8E6",
                "saltpeter=E3E3E3",
                "sapphire=547DDB",
                "sheldonite=C6D5F2",
                "silicon=6B7786",
                "sodalite=6689AF",
                "solar=E17B29",
                "sphalerite=B0BCAA",
                "sulfur=EFC761",
                "sunstone=E56744",
                "tanzanite=4800CE",
                "titanium=3A4E70",
                "topaz=E85F0B",
                "tungsten=3D4047",
                "uranium=57744B",
                "yellorite=E5E200",
                "zanite=7139BC",
                "zinc=B0BCAA"
        };

        @Config.RangeInt(min = 1, max = 20)
        @Config.Name("Sounding Radius Modifier")
        @Config.Comment
                ({
                        "The amount each level of the Sounding infusion enchant will increase the search radius when scanning",
                        "for ores. The calculation of this value is equal to: radius = 4 + (level * modifier)"
                })
        public int soundingRadiusModifier = 4;
    }

    public static class MiscTweaksCategory {
        @Config.RequiresMcRestart
        @Config.Name("Alchemical Brass Requires Copper")
        @Config.Comment("Enables alchemical brass ingots to require copper instead of iron to create.")
        public boolean alchemicalBrassCopperRecipe = false;

        @Config.RequiresMcRestart
        @Config.Name("Disable Crafting Recipe Aspects")
        @Config.Comment("Disables Thaumcraft's dynamic aspect generation from crafting recipes.")
        public boolean disableRecipeAspectsCrafting = false;

        @Config.RequiresMcRestart
        @Config.Name("Disable Crucible Recipe Aspects")
        @Config.Comment("Disables Thaumcraft's dynamic aspect generation from crucible recipes.")
        public boolean disableRecipeAspectsCrucible = false;

        @Config.RequiresMcRestart
        @Config.Name("Disable Infusion Recipe Aspects")
        @Config.Comment("Disables Thaumcraft's dynamic aspect generation from infusion recipes.")
        public boolean disableRecipeAspectsInfusion = false;

        @Config.RequiresMcRestart
        @Config.Name("Legacy Thaumometer Scanning")
        @Config.Comment("Restores the original scanning functionality from 1.7.10 (Thaumcraft 4).")
        public boolean legacyThaumometerScanning = false;

        @Config.Name("Sky Scan Dimensions")
        @Config.Comment("A list of dimension ids where the Thaumometer can be used to scan the sky to obtain research notes.")
        public int[] skyDimensions = new int[] {};
    }

    public static class PechCategory {
        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Item Value Divisor")
        @Config.Comment("")
        public int itemValueDivisor = 2;

        @Config.RangeInt(min = 1, max = 500)
        @Config.Name("Max Trade Value")
        @Config.Comment("The maximum trade value any item can have when offered to a Pech trader.")
        public int maxTradeValue = 32;
    }

    public static class PorousStoneCategory {
        @Config.RequiresMcRestart
        @Config.Name("Enable Porous Stone Tweaks")
        @Config.Comment("Enable Porous Stone drop tweaks. CraftTweaker and GroovyScript methods require this option set to true.")
        public boolean enablePorousStoneTweaks = true;

        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Gravel Drop Modifier")
        @Config.Comment("The chance that gravel will drop when harvesting porous stone. Special item drops have a ((1 + fortune) / gravelModifier) percent chance of occurring.")
        public int gravelDropModifier = 15;

        @Config.RangeInt(min = 1, max = 100)
        @Config.Name("Special Item Drops")
        @Config.Comment("The number of items dropped when a special item drop occurs.")
        public int specialItemDrops = 1;
    }

    public static class PrimalCrusherCategory {
        @Config.RequiresMcRestart
        @Config.Name("Set Unbreakable")
        @Config.Comment("Makes the Primal Crusher unbreakable.")
        public boolean unbreakable = false;

        @Config.RequiresMcRestart
        @Config.RangeInt(min = 1, max = 4)
        @Config.Name("Refining Level")
        @Config.Comment("The Refining infusion enchantment level added to the Primal Crusher by default")
        public int refiningLevel = 1;
    }

    public static class ResearchTableCategory {
        @Config.Name("Research Item Pulling")
        @Config.Comment("Allows the Research Table to use and consume items from nearby inventories for research. Table will search up to 3 blocks away from the Research Table for inventories.")
        public boolean researchTablePulling = false;

        @Config.RangeInt(min = 1, max = 12)
        @Config.Name("Research Aid Vertical Search Distance")
        @Config.Comment("Adjusts the vertical search area when the Research Table scans nearby blocks for Research Aids.")
        public int aidSearchVertical = 1;

        @Config.RangeInt(min = 1, max = 12)
        @Config.Name("Research Aid Horizontal Search Distance")
        @Config.Comment("Adjusts the horizontal search area when the Research Table scans nearby blocks for Research Aids.")
        public int aidSearchHorizontal = 4;
    }

    public static class ThaumonomiconCategory {
        @Config.Name("Disable Thaumonomicon Zoom")
        @Config.Comment("Disables the scroll wheel zoom feature while browsing Thaumonomicon categories.")
        public boolean disableThaumonomiconZoom = false;
    }

    public static class VisGeneratorCategory {
        @Config.RangeInt(min = 1, max = 10000000)
        @Config.Name("RF Capacity")
        @Config.Comment("The maximum amount of RF the Vis Capacitor can hold. This value also controls the amount of RF generated per point of Vis.")
        public int capacity = 1000;

        @Config.RangeInt(min = 1, max = 10000000)
        @Config.Name("Max RF Extraction")
        @Config.Comment("The maximum amount of RF that can be extracted from the Vis Capacitor per tick.")
        public int maxExtract = 20;
    }

    public static class VoidRobesCategory {
        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Helm Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Void Thaumaturge Hood.")
        public int visDiscountHelm = 5;

        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Robes Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Void Thaumaturge Robes.")
        public int visDiscountChest = 5;

        @Config.RangeInt(min = 0, max = 100)
        @Config.Name("Leggings Vis Discount")
        @Config.Comment("The Vis discount granted when wearing the Void Thaumaturge Leggings.")
        public int visDiscountLeggings = 5;
    }

    public static class VoidSiphonCategory {
        @Config.RequiresMcRestart
        @Config.RangeInt(min = 1, max = 100000)
        @Config.Name("Rift Power Required")
        @Config.Comment("Modifies the amount of rift power required to generate a Void Seed. Smaller values will increase the speed Void Seeds are created.")
        public int progressRequired = 2000;
    }



    public static class EquipmentTweaksCategory {
        //TODO: Remove ignore tags when the tweaks config is rewritten
        @Config.Ignore
        @Config.Name("Apprentice's Ring Tweaks")
        public ApprenticesRingCategory apprentices_ring = new ApprenticesRingCategory();
        @Config.Ignore
        @Config.Name("Crimson Cult Robes Tweaks")
        public CrimsonCultRobesCategory cult_robes = new CrimsonCultRobesCategory();
        @Config.Ignore
        @Config.Name("Fortress Armor Tweaks")
        public FortressArmorCategory fortress_armor = new FortressArmorCategory();
        @Config.Name("Goggles of Revealing Tweaks")
        public GogglesRevealingCategory goggles_revealing = new GogglesRevealingCategory();
        @Config.Ignore
        @Config.Name("Primal Crusher Tweaks")
        public PrimalCrusherCategory primal_crusher = new PrimalCrusherCategory();
        @Config.Name("Thaumaturge's Robes Tweaks")
        public ThaumaturgeRobesCategory thaumaturge_robes = new ThaumaturgeRobesCategory();
        @Config.Ignore
        @Config.Name("Void Thaumaturge Robes Tweaks")
        public VoidRobesCategory void_robes = new VoidRobesCategory();

        public static class ApprenticesRingCategory {
            @Config.RequiresMcRestart
            @Config.Name("Apprentice's Ring Structure Loot")
            @Config.Comment("Allows the Apprentice's Ring to be rarely found in some vanilla structure chests.")
            public boolean enableStructureLoot = false;

            @Config.RangeInt(min = 1, max = 100)
            @Config.Name("Apprentice's Ring Vis Discount")
            @Config.Comment("The Vis discount granted by the Apprentice's Ring.")
            public int visDiscount = 5;
        }

        public static class CrimsonCultRobesCategory {
            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Helm Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Crimson Cult Hood.")
            public int visDiscountHelm = 1;

            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Robes Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Crimson Cult Robes.")
            public int visDiscountChest = 1;

            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Leggings Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Crimson Cult Leggings.")
            public int visDiscountLeggings = 1;
        }

        public static class FortressArmorCategory {
            @Config.Name("Enable Knockback Resistance")
            @Config.Comment("Gives the Fortress Armor knockback resistance. Chest = 0.4, Legs = 0.3, Helm = 0.3")
            public boolean enableKnockbackResistance = false;
        }

        public static class GogglesRevealingCategory {
            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Goggles Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Goggles of Revealing.")
            public int visDiscountGoggles = 1;
        }

        public static class PrimalCrusherCategory {
            @Config.RequiresMcRestart
            @Config.Name("Set Unbreakable")
            @Config.Comment("Makes the Primal Crusher unbreakable.")
            public boolean unbreakable = false;

            @Config.RequiresMcRestart
            @Config.RangeInt(min = 1, max = 4)
            @Config.Name("Refining Level")
            @Config.Comment("The Refining infusion enchantment level added to the Primal Crusher by default")
            public int refiningLevel = 1;
        }

        public static class ThaumaturgeRobesCategory {
            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Robes Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Thaumaturge's Robes.")
            public int visDiscountChest = 3;

            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Leggings Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Thaumaturge's Leggings.")
            public int visDiscountLeggings = 3;

            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Boots Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Thaumaturge's Boots.")
            public int visDiscountBoots = 2;
        }

        public static class VoidRobesCategory {
            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Helm Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Void Thaumaturge Hood.")
            public int visDiscountHelm = 5;

            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Robes Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Void Thaumaturge Robes.")
            public int visDiscountChest = 5;

            @Config.RangeInt(min = 0, max = 100)
            @Config.Name("Leggings Vis Discount")
            @Config.Comment("The Vis discount granted when wearing the Void Thaumaturge Leggings.")
            public int visDiscountLeggings = 5;
        }
    }

    static {
        ConfigAnytime.register(ConfigTweaksTT.class);
    }
}
