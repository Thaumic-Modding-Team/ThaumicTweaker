# Changelog ThaumicTweaker
## 1.3.1
### Added
- Hand mirrors can now be placed directly onto pedestals

---

## 1.3.0
### Added
- Fully merged Thaumic Inventory Scanning into the mod with major improvements
- Restored Flux Rift drain fix, it has been removed from Thaumcraft Fix since v1.1.10
- Added Nerfed Heal Focus Effect overhaul to prevent the Heal Focus Effect from being too overpowered
### Fixed
- Fixed all void tools re-equipping when repairing
- Fixed most void tools not having `Lesser Sapping` on their tooltips
- Fixed Sword of Zephyr being interrupted when durability is lost during its whirlwind ability
- Fixed Thaumic Augmentation Impetus Diffuser not being affected by ThaumicTweaker Void Siphon tweaks
- Fixed Thaumic Augmentation rift jar being unable to capture rifts when Flux Rift drain fix is active
### Changed
- Modified `TileVoidSiphonMixin` to support upcoming ThaumicAPI handling

---

## 1.2.4
### Added
- Added tweak to adjust Goggles of Revealing vis discount
- Added tweak to adjust Thaumaturge's Armor vis discounts
### Fixed
- Fixed enhanced research command searching for player in the wrong index

---

## 1.2.3
### Added
- Added `/thaumcraft` and `/tc` enhancement, adding additional features and tab completion to in-game commands
- Added `/tc chunkvis`, this allows you to control the amount of vis on any chunk
### Fixed
- Fixed Champion mob effects not matching their name

---

## 1.2.2
### Removed
- Removed GUI position enhancements (merged into Thaumcraft Fix)
- Removed Pech Trade item render fix (merged into Thaumcraft Fix)
- Removed Caster's Gauntlet HUD render fixes (merged into Thaumcraft Fix)
- Removed Caster's Gauntlet keybind fix (merged into Thaumcraft Fix)
- Removed Control Seal: Use fix (merged into Thaumcraft Fix)
- Removed Exploration Research fix (merged into Thaumcraft Fix)
- Removed Flux Rift drain fix (merged into Thaumcraft Fix)
- Removed FXPollution packet fix (merged into Thaumcraft Fix)
- Removed Giant Taintacle render fix (merged into Thaumcraft Fix)
- Removed Primordial Pearl drop fix (merged into Thaumcraft Fix)
- Removed Research Table shift-click fix (merged into Thaumcraft Fix)
- Removed Thaumcraft Banner Phial interaction fix (merged into Thaumcraft Fix)
- Removed Thaumatorium fix (merged into Thaumcraft Fix)

---

## 1.2.1
### Fixed
- Fixed crash when loading server environments

---

## 1.2.0
### Added
- Added enhancement adding comparator output to Arcane Pedestals
- Added enhancement allowing Goggles of Revealing to attach to Thaumium and Void Metal Helmets
- Added fix for plan focus causing errorred GUI textures
- Added tweak disabling item interaction on boiling Crucibles
- Added tweak blacklisting entities from spreading Flux Phage
- Added tweak disabling Thaumonomicon category zoom
- Added tweak giving the Fortress Armor knockback resistance
- Added tweak to override or add Sounding Infusion Enchant particle colors
- Added wuss mode tweak disabling research requirements

---

## 1.1.1
### Fixed
- Fixed mod name
- Fixed a crash when loaded with TickCentral

### Removed
- Removed fix for Eldritch Crab death rotation, it was relocated to Thaumcraft Fix

---

## 1.1.0
### Added
- Added fix for Caster's Gauntlet causing erroring GUI textures when held in mainhand
- Added fix for Caster's Gauntlet GUI not shifting correctly when held in offhand
- Added fix for Flux Rifts not collapsing when fully consumed by Void Siphons
- Added fix for Giant Taintacles spawning with reduced health
- Added fix for Giant Taintacle model render error spam
- Added fix for Golems voiding held items when interacting with Use Seals with "Can use empty hand" enabled
- Added fix for Primordial Pearls spawned by boss kills maintaining momentum
- Added fix for Primordial Pearls spawned by boss kills floating 3-4 blocks above boss kill location
- Added fix for Thaumcraft not rewarding exploration research correctly
- Added enhancement adding an effect over the player's health bar whenenever runic shielding is active
- Added enhancement suppressing Warp events while player is in creative mode
- Added enhancement supporting Research entry subtitles
- Added enhancement supporting Forbidden Knowledge Research subtitles
- Added tweak increasing vertical search range when the research table scans for nearby research aids
- Added tweak supporting additional Crucible heat sources
- Added tweak allowing mobs to spawn as Champions
- Added tweak to use overhauled Runic Shielding system
- Added tweak allowing increasing or decreasing damage dealt by Liquid Death
- Added tweak allowing Fortune to increase Refining infusion cluster drop chance
- Added tweak to modify Refining infusion cluster drop chance
- Added tweak to modify Sounding infusion search radius
- Added scripting method to add addition drops to Collapsing Flux Rifts

### Changed
### Fixed
- Fixed GroovyScript example generation resulting in invalid SpecialMiningResult code
- Fixed GroovyScript PechTrade addition methods throwing incorrect error

---

## 1.0.1
### Fixes
- Fixed crash caused by unchecked EntityPlayer cast on damage event

---

## 1.0.0
First release!
