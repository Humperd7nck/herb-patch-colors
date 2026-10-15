# Herb Patch Colors

A RuneLite plugin for customizable herb patch overlays and a simple herb-readiness indicator. Community-maintained fork of [NODDZY's Herb Patch Overlay](https://github.com/NODDZY/herbpatch-overlay), licensed under BSD 2-Clause (see [LICENSE](LICENSE)). This fork is not affiliated with RuneLite or the original author.

## Features

- Customizable colors for herb patch growth states, with hull, tile and outline drawing modes.
- Correct Weiss herb patch targeting (object ID `33176`), instead of the adjacent Hardy goutweed patch.
- Optional **Highlight composted herbs** overlay color, using RuneLite Time Tracking compost records. This color replaces **Growing** only; other growth states retain their own colors.
- Optional **Herbs Ready HUD** showing estimated harvest readiness for all ten herb patches, including patches away from the current location.

## Herbs Ready HUD (v0.2.0-rc2)

Enable **Show Herbs Ready HUD** under **Herbs Ready HUD** to display a small, movable indicator showing a herb leaf icon below a short message. The feature is **Off by default**, so the existing patch overlays are unchanged unless it is enabled.

- **Herbs Ready** — light-green background; shown when all ten herb patches are predicted harvestable.
- **Not Ready** — light-red background; only shown if **Show Not Ready** is enabled (Off by default).
- Otherwise, the HUD is hidden. It never displays counts, timers, or a patch list.

### Settings

| Setting | Default | Description |
| --- | --- | --- |
| Show Herbs Ready HUD | Off | Display the herb indicator when all patches are predicted ready |
| Show Not Ready | Off | Also show the red indicator when at least one patch is not ready |
| HUD size (%) | 100 | Adjust indicator size, 75–150% |
| Ready background | Light green | Background color of the ready indicator |
| Not Ready background | Light red | Background color of the optional not-ready indicator |
| Text color | Black | Change the indicator text color |
| Background opacity (%) | 100 | Change the transparency of the colored background |

Use RuneLite's standard **Alt-drag** overlay positioning to move the indicator. RuneLite remembers its position.

### Readiness predictions

The indicator uses the current RuneScape profile's saved **Time Tracking** herb patch observations and farming tick offset. Predictions are refreshed approximately every five seconds while logged in, including when the player is away from the herb patches. The ten supported patches are Falador, Catherby, Ardougne, Port Phasmatys, Harmony Island, Troll Stronghold, Hosidius, Weiss, Farming Guild, and Civitas illa Fortis (Varlamore).

**Limitations:** These are predictions, not remote observations. A patch may become diseased or die while the player is elsewhere; Time Tracking may still predict it to be harvestable until it is visited again. Empty, dead, diseased, untracked, and non-herb patches do not count as ready. The plugin does not modify Time Tracking records, and predictions are specific to the currently selected RuneScape profile.

## Other requirements and limitations

- Compost highlighting depends on RuneLite Time Tracking's saved farming data, which may be unavailable or stale until the relevant patches have been visited.
- Disable the original Herb Patch Overlay when using this fork to avoid overlapping scene highlights.

## Development and build

Requires Java 11. Run `./gradlew clean build` (or `gradlew.bat clean build` on Windows). GitHub Actions also builds the source and uploads a JAR artifact.

## Release status

- **v0.1.1** is the current Plugin Hub release (accepted October 14, 2026).
- **v0.2.0-rc2** adds the redesigned Herbs Ready HUD and requires a new source build, live verification, and Plugin Hub update review before publishing. It is not yet submitted.

## Credits and license

Based on **Herb Patch Overlay** by NODDZY. Original copyright and BSD 2-Clause license retained in [LICENSE](LICENSE). This fork's changes include corrected Weiss targeting, compost-sensitive colors and the optional Herbs Ready HUD.
