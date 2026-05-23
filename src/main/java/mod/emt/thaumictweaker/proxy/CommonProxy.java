package mod.emt.thaumictweaker.proxy;

import mod.emt.thaumictweaker.compat.crafttweaker.CrafttweakerTT;
import mod.emt.thaumictweaker.config.ConfigOverhaulsTT;
import mod.emt.thaumictweaker.config.ConfigTweaksTT;
import mod.emt.thaumictweaker.events.ChampionMobHandler;
import mod.emt.thaumictweaker.events.RunicShieldingHandler;
import mod.emt.thaumictweaker.network.PacketHandlerTT;
import mod.emt.thaumictweaker.util.libs.ModIds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;

import java.util.ArrayList;
import java.util.List;

public class CommonProxy {
    public void preInit() {
        if(ModIds.crafttweaker.isLoaded) MinecraftForge.EVENT_BUS.register(new CrafttweakerTT());

        if(ConfigOverhaulsTT.championMobOverhaul) MinecraftForge.EVENT_BUS.register(new ChampionMobHandler());
        if(ConfigOverhaulsTT.runicShieldingOverhaul) MinecraftForge.EVENT_BUS.register(new RunicShieldingHandler());
    }

    public void init() {
        PacketHandlerTT.init();
    }

    public void postInit() {
        if (ConfigTweaksTT.furious_zombie.enableFuriousZombieSpawning) {
            EntityRegistry.addSpawn(EntityGiantBrainyZombie.class, ConfigTweaksTT.furious_zombie.spawnWeight, 1, 1, EnumCreatureType.MONSTER, getEntityBiomes(EntityBrainyZombie.class));
        }
    }

    // Gets biomes from selected entity.
    public static Biome[] getEntityBiomes(Class<? extends Entity> spawn) {
        List<Biome> biomes = new ArrayList<>();

        for(Biome biome : Biome.REGISTRY) {
            List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.MONSTER);

            for(Biome.SpawnListEntry list : spawnList)
                if(list.entityClass == spawn) {
                    biomes.add(biome);
                    break;
                }
        }

        return biomes.toArray(new Biome[0]);
    }
}
