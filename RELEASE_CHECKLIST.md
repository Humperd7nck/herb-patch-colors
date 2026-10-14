# Release checklist

- [x] Preserve original NODDZY BSD-2-Clause copyright/license.
- [x] Root-level Gradle project layout and GitHub Actions workflow included.
- [x] Rename Gradle artifact and Plugin Hub display metadata.
- [x] Record user-confirmed Weiss and compost behavior.
- [ ] Upload contents (not enclosing directory) to the repository root; preserve `.github/workflows/build.yml`.
- [ ] Verify the GitHub Actions build passes after the upload.
- [ ] Check any remaining conflicts with existing Herb Patch Overlay/plugin hub duplicate policy.
- [ ] Confirm all growth states and other patch locations in game.
- [ ] Copy final full commit SHA and submit `plugins/herb-patch-colors` manifest PR to runelite/plugin-hub after review.

The manifest should contain exactly:

    repository=https://github.com/Humperd7nck/herb-patch-colors.git
    commit=<FULL_40_CHARACTER_COMMIT_SHA_OF_FINAL_SOURCE>

Do not use an earlier commit hash or a ZIP/JAR URL as the `commit` value.
