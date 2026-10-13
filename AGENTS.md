# Project overview

This is a small Android application written in Kotlin.
The application aims to combine food tracking, insulin management, carbohydrate calculations,
and personal logging in one convenient interface.

The project is primarily for learning Android development, so prefer clear,
simple, idiomatic solutions over unnecessarily abstract or complex ones.

# Project direction

The intended development order is roughly:
1. Build the core UI and navigation.
2. Implement a local food database.
3. Add external food database.
4. Implement insulin settings.
5. Implement carbohydrate calculator.
6. Implement logbook.
7. Implement reminders.


# Technology

- Kotlin
- Jetpack Compose for UI
- Gradle Kotlin DSL
- AndroidX libraries where appropriate

# Architecture

Keep the project simple.

- UI code belongs in composables.
- Application state and non-trivial logic should not be embedded directly
  inside composables.
- Prefer ViewModels for screen state and application logic when appropriate.
- Data access should be separated from UI logic.
- Avoid introducing additional architectural layers unless they provide a
  clear benefit.

# Code style

- Prefer readable code over clever code.
- Use descriptive names.
- Keep functions reasonably small.
- Avoid unnecessary abstractions.
- Avoid duplicated code when a simple shared function is appropriate.
- Prefer Kotlin idioms, but do not make code unnecessarily difficult for a
  beginner to understand.

# Dependencies

- Prefer Android SDK, Kotlin standard library, and AndroidX functionality
  before introducing third-party libraries.
- Do not add a new dependency unless it provides a clear benefit.
- Explain why a new dependency is needed before adding it.

# Testing

- Add or update tests when changing meaningful application logic.
- Prefer local unit tests for logic that does not require Android.
- Use instrumented or Compose UI tests only when Android/UI behavior needs
  to be tested.
- Run the relevant tests after making changes.

# Verification

Before considering a task complete:

1. Make sure the project compiles.
2. Run relevant unit tests.
3. Run lint when appropriate.
4. Do not claim that something works unless it has actually been checked.

# Git and changes

- Keep changes focused on the requested task.
- Do not modify unrelated files.
- Do not reformat large files unnecessarily.
- Do not commit generated build files.
- Never add passwords, API keys, tokens, or other secrets to the repository.

# Working with this project

When asked to implement something substantial:

1. Inspect the existing implementation first.
2. Follow the existing project structure unless there is a good reason not to.
3. Prefer the smallest reasonable change.
4. Explain significant architectural changes.
5. Verify the result after implementation.

When debugging:

1. Investigate the error or logs first.
2. Identify the likely cause before making large changes.
3. Prefer fixing the underlying problem rather than suppressing the symptom.
4. Add a regression test when practical.