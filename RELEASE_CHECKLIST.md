# Herb Patch Colors v0.2.0-rc2 — release and QA checklist

This is an **unpublished HUD update**. Leave the approved Plugin Hub v0.1.1 manifest unchanged until the update is built and tested in RuneLite.

## Preservation

- [x] Preserve the BSD 2-Clause license and attribution to the original author.
- [x] Preserve existing herb patch coloring, compost highlighting and Weiss targeting.
- [x] Keep the existing `herbpatchoverlay` configuration group so saved patch colors remain intact.
- [x] Keep the new HUD switched Off by default.

## HUD tests

- [x] Offline readiness model: herb varieties, harvestability, invalid records, farming tick offsets and Leagues worlds.
- [x] Offline HUD state tests: disabled, ready, and optional not-ready transitions.
- [x] Production HUD Java classes compiled against RuneLite API-compatible stubs; the offline renderer passed 35 color, icon, scale, visibility and config checks.
- [ ] Run the full Gradle build and test suite on Windows (`.\gradlew.bat clean build --no-daemon`).
- [ ] With the HUD enabled, confirm **Herbs Ready** appears on light green when all ten patches are predicted harvestable.
- [ ] With **Show Not Ready** disabled, confirm the HUD disappears if any patch is not predicted ready.
- [ ] Enable **Show Not Ready** and confirm the red indicator appears whenever at least one patch is not ready.
- [ ] Confirm neither HUD state displays patch counts, extra text or timers.
- [ ] Verify the leaf image, custom text and background colors, sizing and background opacity.
- [ ] Verify RuneLite Alt-drag position is saved across restarts.
- [ ] Confirm account/profile switches, harvesting/replanting and missing Time Tracking data are handled correctly.
- [ ] Regression-test normal patch overlays, compost status and Weiss targeting.

## Plugin Hub update after validation

1. Commit and push the tested v0.2.0 source to `Humperd7nck/herb-patch-colors`.
2. Verify the full Gradle build and tests.
3. Get the new source commit hash.
4. Submit a Plugin Hub update PR changing only the `commit=` line in `plugins/herb-patch-colors`.
5. Await maintainer review before treating the update as a public release.

Existing approved manifest (unchanged):

```ini
repository=https://github.com/Humperd7nck/herb-patch-colors.git
commit=30add93a900bd9e7683432cbe385282edec3a73a
```
