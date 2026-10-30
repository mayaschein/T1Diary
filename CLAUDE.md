# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

Project rules (architecture, code style, dependencies, testing, verification, UI conventions) live in AGENTS.md and apply here too:

@AGENTS.md

## Commands

Run from the repo root. On Windows use `.\gradlew.bat` (PowerShell) or `./gradlew` (Git Bash). `JAVA_HOME` is not set in the shell; point it at Android Studio's bundled JDK first, e.g. `export JAVA_HOME="/c/Program Files/Android/Android Studio/jbr"`. A cold build plus lint has taken over 10 minutes, so run long Gradle tasks in the background.

- Build debug APK: `./gradlew assembleDebug`
- Compile only (fast check): `./gradlew :app:compileDebugKotlin`
- Unit tests: `./gradlew testDebugUnitTest`
- Single unit test: `./gradlew testDebugUnitTest --tests "com.example.t1diary.SomeTest.someMethod"` (class or method filter; wildcards like `"*SomeTest"` work)
- Instrumented / Compose UI tests (needs device or emulator): `./gradlew connectedDebugAndroidTest`
- Lint: `./gradlew lintDebug` (report in `app/build/reports/`)
- Install on connected device: `./gradlew installDebug`

## Build setup

- Single module `:app`, package `com.example.t1diary`.
- Dependency versions are centralized in `gradle/libs.versions.toml`; add new libraries there and reference them via `libs.*` in `app/build.gradle.kts`.
- AGP 9 with built-in Kotlin (only the `kotlin.compose` plugin is applied, no separate `kotlin-android` plugin). compileSdk 37, targetSdk 36, minSdk 24, Java 11.

## Architecture (current state)

The app is at step 1 of the roadmap in AGENTS.md (core UI and navigation). There are no ViewModels, data layer, persistence, or tests yet. New tests go in `app/src/test/java/com/example/t1diary/` (local unit tests) or `app/src/androidTest/java/com/example/t1diary/` (instrumented/Compose UI tests); the test dependencies are already configured.

UI code is organized by feature under `com.example.t1diary.ui`:

- `MainActivity.kt` only sets the theme and calls `T1DiaryApp()`.
- `ui/T1DiaryApp.kt` is the app shell. It holds the `Scaffold`, a `TopAppBar` that shows the current screen's title and has Home/Settings actions, the `NavHost`, and the expandable FAB (`HomeFab`), which is shown only on Home. All navigation happens here.
- `ui/navigation/Screen.kt` has the `Screen` enum with each destination's `route` and `@StringRes titleRes`. Always use `Screen.X.route` rather than string literals. `Screen.fromRoute()` maps the current back-stack route to a `Screen`. To add a screen, add an enum entry, a `composable(...)` in `T1DiaryApp`, and its title string.
- `ui/home/`, `ui/logbook/`, `ui/food/` and `ui/settings/` each hold one screen. A screen's ViewModel will go in the same folder. Screens take navigation callbacks such as `onLogbookClick` instead of a `NavController`, which keeps previews simple. Logbook, Food Database and Settings are still placeholders.
- `ui/theme/` holds the Material 3 theme (`T1DiaryTheme`). Wrap every `@Preview` in it.
- All user-visible text, including content descriptions, lives in `res/values/strings.xml` and is read with `stringResource(...)`.
- A top-level `data/` package is planned for roadmap step 2 (Room). It doesn't exist yet.
- `.idea/` and `.kotlin/` are gitignored. Don't add files from them.
- `docs/ui_plan.md` describes the intended screens and the planned sub-navigation (logbook → calendar/add note/entry details/search; food database → list/details/create/search; settings → insulin/reminders). Follow it when adding screens or routes.
- `README.md` describes the full feature set (food DB with offline/custom foods and recipes, I:C ratios, basal settings, pump vs. pen modes, carb calculator, logbook with glucose tracking, reminders).
