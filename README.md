# ThaumicTweaker

[![Requires MixinBooter](https://img.shields.io/badge/Requires-MixinBooter-3498db.svg?labelColor=34495e&style=for-the-badge)](https://www.curseforge.com/minecraft/mc-mods/mixin-booter)
[![Requires ConfigAnytime](https://img.shields.io/badge/Requires-ConfigAnytime-3498db.svg?labelColor=34495e&style=for-the-badge)](https://www.curseforge.com/minecraft/mc-mods/configanytime)

Tweaks for Thaumcraft 6. All the Thaumcraft tweaks a tweaker could want.

---

## Fixes
- Fixed Boots of the Traveler and Cloud Ring screen shake and damage sound when taking zero fall damage
- Fixed Hand Mirror not being able to be placed on pedestals
- Fixed Giant Taintacles spawning with reduced health
- Fixed Primal Crusher and all void tools re-equipping when repairing
- Fixed most void tools not having `Lesser Sapping` on their tooltips
- Fixed Thaumometer displaying particles when stored in first hotbar slot
- Fixed champions potentially having the wrong name in servers
- Fixed Flux Fifts not collapsing when completely being drained by machines and devices such as Void Siphons
- Fixed Sword of Zephyr being interrupted when durability is lost during its whirlwind ability
- Fixed Magical Hand Mirrors not being placeable on pedestals
- Fixed Firebat hitbox eye height

---

## Enhancements
**NOTE:** All enhancements listed in this category are enabled by default. 

- **Armor Enhancements:**
  - Goggles of Revealing can now be attached to Thaumium and Void Metal Helmets with infusion
- **Focus Effects:** 
  - Revamps the cast sounds of certain focus effects to provide better variety (Break, Earth, Exchange, Frost, Heal, Rift)
  - Adds impact sounds to various focus effects that lack any (Break, Earth, Exchange, Fire, Flux , Frost, Heal, Rift)
  - Makes several focus mediums play additional cast sounds to differentiate them (Bolt, Cloud, Mine, Spellbat)
- **Gui Enhancements:**
  - Add Runic Warding effect over health bar whenever warding is active
  - Add subtitles support to Thaumonomicon research entries
  - Add subtitles support to Forbidden Knowledge research entries
- **Misc Enhancements:**
  - Add comparator output values to Arcane Pedestal
  - Add tooltip to items that can be used as research aids
  - Add improved in-game commands with tab completion'
  - Add `/tc chunkvis`, this allows you to control the amount of vis on any chunk
  - Suppress Warp Events while player is in Creative mode
- **Thaumometer Enhancements:**
  - Add inventory scanning support for Thaumometers

---

## Overhauls
**NOTE:** All overhauls listed in this category are disabled by default.

- **Champion Mob Overhaul:**
  - Enables the Champion Mob system from previous Thaumcraft versions
- **Nerfed Heal Focus Effect**
  - Replaces the direct healing from Focus Effect: Heal with Regeneration III 
- **Loot Table Overhaul:**
  - Changes most hardcoded mob drops into loot tables that can be modified using loot table tweakers. A full list of loot tables can be found [here](https://github.com/Elite-Modding-Team/ThaumicTweaker/tree/main/src/main/resources/assets/thaumictweaker/loot_tables/entity).
- **Runic Shielding Overhaul:**
  - Changes Runic Shielding to use its own shielding system instead of using Minecraft's Absorption. This also fixes a few issues where Thaumcraft would reset absorption granted by other effects.

---

## CraftTweaker/GroovyScript
**NOTE:** Scripting methods and their uses can be found on the mod [wiki](https://github.com/Elite-Modding-Team/ThaumicTweaker/wiki).

- **Collapsing Flux Rift Drops:**
  - Add additional items that drop when collapsing Flux Rifts using CraftTweaker or GroovyScript
- **Pech Trades:**
  - Add and remove Pech trades using CraftTweaker or GroovyScript
  - Add Pech valued items using CraftTweaker or GroovyScript
- **Porous Stone Tweaks:**
  - Add or remove Porous Stone special item drops using CraftTweaker or GroovyScript
- **Special Mining Result Tweaks:**
  - Add and remove special mining drops using CraftTweaker or GroovyScript

---

## General Tweaks
**NOTE:** All tweaks listed in this category are disabled by default or use Thaumcraft default values. 

- **Apprentice's Ring Tweaks:**
  - Allow the Apprentice's Ring to be looted from some vanilla structures
  - Modify the Vis Discount granted by the Apprentice's Ring
- **Champion Mob Tweaks:**
  - Add unimplemented Thaumcraft Champion mob content
  - Define what mobs can become champions as well as the chance specific mobs can become champions
- **Crucible Tweaks:**
  - Add additional blocks that can heat the Crucible
  - Add tweak disabling right-click item smelting interaction
- **Cult Robes Tweaks:**
  - Modify the Vis discount granted by each piece of armor
- **Curiosity Tweaks:**
  - Ancient Curiosity, Arcane Curiosity, and Preserved Curiosity can be obtained from Pech trades
  - Eldritch Curiosity drops from Eldritch Guardians
  - Twisted Curiosity drops from Giant Taint Seeds
- **Flux Phage Tweaks:**
  - Blacklist entities that can be infected with and spread Flux Phage
- **Flux Pollution Tweaks:**
  - Adjust the amount of flux generated by the Essentia Mirror
  - Adjust the amount of flux generated by the Mirror
- **Fortress Armor Tweaks:**
  - Add knockback resistance to Fortress Armor
- **Furious Zombie Tweaks:**
  - Allow Furious Zombies to spawn naturally
- **Goggles of Revealing:**
  - Modify the Vis Discount granted by the Goggles of Revealing
- **Golem Material Tweaks:**
  - Change golem material stats such as armor, health, damage, and crafting material
- **Liquid Death Tweaks:**
  - Increase or decrease the amount of damage done by Liquid Death
- **Infusion Enchantment Tweaks:**
  - Adjust the chance Refining infusion will drop ore clusters
  - Allow the Refining infusion cluster drop chance to be affected by Fortune
  - Change or add Sounding infusion ore detection colors
  - Change the radius the Sounding infusion will detect ores
- **Pech Trade Tweaks:** 
  - Change Pech item value calculation
- **Porous Stone Tweaks:**
  - Change the chance special items will drop when harvesting Porous Stone
  - Increase the number of special items that drop when harvesting Porous Stone
- **Primal Crusher Tweaks:**
  - Modify Primal Crusher Refining infusion enchantment level
  - Make Primal Crusher Unbreakable
- **Research Table Tweaks:**
  - Research table can use and consume items from nearby inventories for research
  - Increase the vertical search area when the Research Table looks for nearby research aids
- **Thaumaturge's Robes Tweaks:**
  - Modify the Vis discount granted by each piece of armor
- **Thaumonomicon Tweaks:**
  - Disable zoom while browsing Thamonomicon categories
- **Vis Generator Tweaks:** 
  - Change the amount of RF generated per point of Vis and the RF output per tick
- **Void Siphon Tweaks:** 
  - Increase Void Seed creation speed
- **Void Thaumaturge Robes Tweaks:** 
  - Modify the Vis discount granted by each piece of armor
- **Miscellaneous Tweaks:**
  - Allow Thaumometer sky scanning from non-overworld dimensions
  - Change Alchemical brass recipe to use copper instead of iron
  - Disable Thaumcraft's item aspect calculation based on item crafting recipe 
  - Restores Legacy Thaumometer scanning functionality from 1.7.10


---

## Wuss Mode Tweaks
**NOTE:** All tweaks listed in this category are disabled by default. Wuss Mode Tweaks are designed to reduce the cost of certain recipes or allow players to bypass certain mechanics.
- **Research:**
  - Disable any or all Crafting, Item, Observation, or Theory research requirements
- **Recipes:**
  - **Arcane Bore:** Makes the Arcane Bore recipe cheaper
  - **Bath Salts:** Makes the Bath Salts recipe cheaper
  - **Sanitizing Soap:** Makes the Sanitizing Soap recipe cheaper
  - **Workbench Charger:** Makes the Workbench Charger recipe cheaper
- **Eldritch Guardian:** 
  - Removes the warp gain when Eldritch Guardians spawn in line of sight of the player
- **Primordial Pearl Recipes:**
  - New Infusion recipe that creates a 1 durability Primordial Pearl
  - New Infusion recipe that increases Primordial Pearl durability
- **Void Siphon:**
  - Void siphon no longer requires a nearby rift and will slowly accumulate progress over time

---

## Incompatible/Superseded Addons
These Thaumcraft addons are either not compatible with ThaumicTweaker or are not recommended because there are already similar features to them already included.

- **Incompatible:**
  - [TC4 Research Port](https://www.curseforge.com/minecraft/mc-mods/oldresearch) - Some of ThaumicTweaker's features will overlap when this addon is installed.
  - [Thaumic Forever](https://www.curseforge.com/minecraft/mc-mods/thaumic-forever) - Incompatible. Most of ThaumicTweaker's features will break alongside this addon.
  - [Thaumic Staff](https://www.curseforge.com/minecraft/mc-mods/thaumic-staff) - Breaks all of ThaumicTweaker's research table tweaks.
  - [Thaumic Wands](https://www.curseforge.com/minecraft/mc-mods/thaumic-wands) - Breaks all of ThaumicTweaker's research table tweaks.
- **Superseded:**
  - [Stable Thaumometer](https://www.curseforge.com/minecraft/mc-mods/stable-thaumometer) - Fix already included in ThaumicTweaker.
  - [Thaumcraft 6 Enhanced](https://www.curseforge.com/minecraft/mc-mods/thaumcraft-6-enhanced) - Incompatible and superseded by ThaumicTweaker's `Runic Shielding Overhaul` tweak in `ThaumicTweaker - Overhauls.cfg`.
  - [Thaumcraft No Research](https://www.curseforge.com/minecraft/mc-mods/thaumcraft-no-research) - Superseded by ThaumicTweaker's `Disable Research Requirements` tweak in `ThaumicTweaker - Wuss Mode.cfg`.
  - [Thaumic Inventory Scanning](https://www.curseforge.com/minecraft/mc-mods/thaumcraft-inventory-scanning) - Fully merged into ThaumicTweaker and improved.
  - [Thaumic Meme](https://www.curseforge.com/minecraft/mc-mods/thaumic-meme) - Superseded by ThaumicTweaker's `Enable Research Subtitles` tweak in `ThaumicTweaker - Enhancements.cfg`.
  - [Thaumic Mercy](https://www.curseforge.com/minecraft/mc-mods/thaumic-mercy) - Superseded by ThaumicTweaker's `Disable Research Requirements` tweak in `ThaumicTweaker - Wuss Mode.cfg`.
  - [ThaumTweaks](https://www.curseforge.com/minecraft/mc-mods/thaumtweaks) - Incompatible. The majority of its features have been rewritten and improved in ThaumicTweaker.
