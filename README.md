# Herb Patch Colors

A RuneLite plugin highlighting herb patch growth states. Community-maintained fork of [NODDZY's Herb Patch Overlay](https://github.com/NODDZY/herbpatch-overlay), licensed under BSD 2-Clause (see [LICENSE](LICENSE)). This fork is not affiliated with RuneLite or the original author.

## Features

- Customizable overlay colors for herb patch growth states; supports the existing hull, tile and outline drawing options.
- Correct Weiss herb patch selection using object ID `33176` (the original plugin targeted adjacent Hardy goutweed).
- Optional **Highlight composted herbs** overlay color, using existing RuneLite Time Tracking compost records. The compost color overrides **Growing** only; Empty, Overgrown, Mature, Diseased and Dead keep their own state colors.
- Unknown or unavailable compost tracking data falls back to the normal Growing color.

## Requirements and limitations

- Compost status depends on RuneLite Time Tracking's saved RS-profile farming data and may be unavailable or stale if that data has not been recorded. The plugin does not modify Time Tracking records.
- Disable the original Herb Patch Overlay when using this fork to avoid overlapping overlays.

## Development and build

Requires Java 11. Run `./gradlew clean build` (or `gradlew.bat clean build` on Windows). GitHub Actions also builds and uploads the compiled JAR as an artifact.

## Verification

The maintainer has confirmed in RuneLite that the standalone plugin loads, Weiss targets the correct patch, and compost highlighting works. A passing GitHub Actions build has also been reported. Other patch locations, every growth state, account/profile switching, and all compost types have **not** been independently exhaustively regression-tested. Plugin Hub approval has **not** been granted.

## Credits and license

Based on **Herb Patch Overlay** by NODDZY. Original copyright and BSD 2-Clause license retained in [LICENSE](LICENSE). Changes in this fork include Weiss targeting and compost-sensitive coloring.
