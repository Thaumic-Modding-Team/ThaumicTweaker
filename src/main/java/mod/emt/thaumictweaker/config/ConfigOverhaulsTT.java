package mod.emt.thaumictweaker.config;

import com.cleanroommc.configanytime.ConfigAnytime;
import mod.emt.thaumictweaker.ThaumicTweaker;
import net.minecraftforge.common.config.Config;

@Config.LangKey("config." + ThaumicTweaker.MOD_ID + ":overhauls")
@Config(
        modid = ThaumicTweaker.MOD_ID,
        name = ThaumicTweaker.MOD_ID + "/" + ThaumicTweaker.MOD_NAME + " - Overhauls"
)
public class ConfigOverhaulsTT {
    @Config.RequiresMcRestart
    @Config.Name("Champion Mob Overhaul")
    @Config.Comment("Enables the Champion mob system from older Thaumcraft versions.")
    public static boolean championMobOverhaul = false;

    @Config.Name("Champion Mob Settings")
    public static ChampionMobsCategory championMobSettings = new ChampionMobsCategory();

    @Config.RequiresMcRestart
    @Config.Name("Loot Table Overhaul")
    @Config.Comment("Changes Thaumcraft mobs and bosses so loot is generated from loot tables instead of hardcoded drops.")
    public static boolean lootTableOverhaul = false;

    @Config.Name("Nerfed Heal Focus Effect")
    @Config.Comment("Changes Focus Effect: Heal to a regeneration effect instead of an instant heal.")
    public static boolean nerfedHealFocus = false;

    @Config.RequiresMcRestart
    @Config.Name("Runic Shielding Overhaul")
    @Config.Comment("Enables Runic Shielding overhaul, replacing absorption hearts with a new shield system.")
    public static boolean runicShieldingOverhaul = false;

    public static class ChampionMobsCategory {
        @Config.Name("Champion Bosses")
        @Config.Comment("Allows boss mobs to transform into champion mobs.")
        public boolean championBosses = false;

        @Config.Name("Global Champions")
        @Config.Comment("All mobs have a chance to become champions regardless of whitelist settings.")
        public boolean globalChampions = false;

        @Config.Name("Champion Whitelist")
        @Config.Comment
                ({
                        "A whitelist of mobs that can spawn as champions. A bonus chance of 0 will give mobs a ~1% chance of becoming a champion.",
                        "Format:  entityid=bonuschance",
                        "  entityid - the entity registry name",
                        "  bonuschance - the bonus chance this mob will spawn as a champion. Must be between 0 and 100",
                        "Examples:",
                        "  minecraft:zombie=8",
                        "  minecraft:skeleton=5",
                        "  minecraft:enderman=0"
                })
        public String[] championWhitelist = new String[] {
                "minecraft:blaze=0",
                "minecraft:enderman=0",
                "minecraft:husk=0",
                "minecraft:skeleton=0",
                "minecraft:spider=0",
                "minecraft:stray=0",
                "minecraft:witch=1",
                "minecraft:wither_skeleton=1",
                "minecraft:zombie=0",
                "thaumcraft:brainyzombie=0",
                "thaumcraft:eldritchcrab=0",
                "thaumcraft:taintacle=2",
                "thaumcraft:inhabitedzombie=3"
        };

        @Config.Name("Biome Type Modifier")
        @Config.Comment
                ({
                        "Biome types that increase the chance a mob will spawn as a champion.",
                        "Format:",
                        "  BIOMETYPE=chanceModifier"
                })
        public String[] biomeTypeModifier = new String[] {
                "SPOOKY=2",
                "NETHER=2",
                "END=2"
        };
    }

    static {
        ConfigAnytime.register(ConfigOverhaulsTT.class);
    }
}
