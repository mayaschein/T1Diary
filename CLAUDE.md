# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

Project rules (architecture, code style, dependencies, testing, verification, UI conventions) live in AGENTS.md and apply here too:

@AGENTS.md

## Commands

Run from the repo root. On Windows use `.\gradlew.bat` (PowerShell) or `./gradlew` (Git Bash).

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

- `MainActivity.kt` contains everything UI-related right now: `T1DiaryApp()` hosts a `Scaffold` with a `TopAppBar` (Home/Settings actions), a `NavHost` with string routes (`home`, `logbook`, `food_database`, `settings`), and an expandable FAB (`HomeFab`, "Add Note"/"Add Food") shown only on the `home` route. Screens are placeholder composables.
- `ui/theme/` holds the Material 3 theme (`T1DiaryTheme`); wrap previews in it.
- `docs/ui_plan.md` describes the intended screens and the planned sub-navigation (logbook → calendar/add note/entry details/search; food database → list/details/create/search; settings → insulin/reminders). Follow it when adding screens or routes.
- `README.md` describes the full feature set (food DB with offline/custom foods and recipes, I:C ratios, basal settings, pump vs. pen modes, carb calculator, logbook with glucose tracking, reminders).
