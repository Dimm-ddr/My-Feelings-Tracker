# My Feelings Tracker — Claude Instructions

## Build & test

- Build and run via Android Studio (Shift+F10), not Gradle tasks directly
- Instrumented tests require a connected device or running emulator: Run > Run '...(androidTest)'
- Unit tests: `./gradlew test`
- Line length limit: **100 characters** (enforced by linter)

## Package naming

- App package: `io.github.dimmddr.myfeelingstracker`
- No underscores in package names — the linter rejects them (standard:package-name)

## Architecture & key invariants

### EmotionCategory ↔ WheelGeometry coupling

`EmotionCategory.entries` order must match sector indices 0–7 defined in `WheelGeometry.kt`.
Sector-to-category mapping relies entirely on ordinal position — there is no explicit mapping
structure.
**Do not reorder `EmotionCategory` entries or add new ones without updating `WheelGeometry`
accordingly.**

Current order (index = sector), clockwise in screen coordinates (y-axis points down):

0. ANTICIPATION
1. JOY
2. TRUST
3. FEAR
4. SURPRISE
5. SADNESS
6. DISGUST
7. ANGER

### SVG files

- Emotion wheel SVGs live in `res/raw/` and are rendered via `coil-svg` (not Coil's default bitmap
  decoder)
- `emotion_wheel.svg` — petals only (primary use)
- `emotion_wheel_with_circles.svg` — petals + dashed circles for blended emotion zones
- Both SVGs have a manually adjusted `viewBox="149 217 621 621"` to crop empty canvas space from the
  Figma export — do not reset this to the original `0 0 939 944`

## Testing rules

- **No mocking the database.** Instrumented tests use real in-memory Room databases (
  `Room.inMemoryDatabaseBuilder`). Mock/prod divergence has caused issues before.
- **No tests for pure delegation.** `EmotionRepository` only delegates to the DAO one-to-one — it is
  intentionally untested. All persistence logic is covered by `EmotionDaoTest`.
- **No circular/tautological tests.** Tests must assert observable behavior, not just repeat the
  implementation.

## UI & theming

- Spacing constants are in `AppSpacings` (inside `Theme.kt`): `Small = 8dp`, `Medium = 16dp`,
  `Large = 32dp`
- Light theme background is warm gray `#F0EDE8` — chosen for contrast with pale petal tips in the
  wheel SVG
- Dark theme is not yet implemented
