# Herb Patch Colors (community fork)

Standalone source fork of NODDZY's Herb Patch Overlay. Original attribution and BSD-2-Clause LICENSE retained.

## Changes
- Weiss: map to object ID **33176** as observed via RuneLite Identificator (previously targeted Hardy goutweed).
- New **Highlight composted herbs** toggle and color picker. Only affects **GROWING** state. Empty, overgrown, mature, diseased, and dead continue using their original color rules.
- Compost data is **read-only** from RuneLite's **Time Tracking** (Farming Tracker) RS-profile settings. No attempt is made to infer compost solely from growth state. Missing/unknown record → normal Growing color. The underlying Time Tracking data must be available and up to date.

## Build
Push all files to GitHub and run Actions → Build with Gradle → Run workflow, or use `./gradlew build` with Java 11. Tests/build have **not** been run in this environment. The workflow is configured to run on `main` and `master`.

## QA before release
1. Verify that object ID 33176 appears as a spawned GameObject and the Weiss overlay stays attached to the correct patch after teleport or reload.
2. Enable RuneLite Time Tracking and test compost applied before and after planting, with regular/super/ultracompost; verify blue on growing herbs and yellow when status is unknown.
3. Check each existing growth state (especially overgrown, empty, diseased, dead, mature) overrides compost color.
4. Test other nine herb patches and account switch/profile persistence.
5. Review Plugin Hub policies concerning substantial duplicate functionality before submitting as a new entry.
