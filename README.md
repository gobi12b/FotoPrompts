# Foto Prompts

An Android app with a searchable library of ready-to-copy AI image prompts for turning your photos into stylized images with **ChatGPT** or **Gemini**. Browse by style, copy a prompt to your clipboard, or share it straight into an AI app — then attach your photo and send.

## Features

- **100+ curated prompts** across styles like Trending (action figure, Studio Ghibli, old money, 90s Polaroid, LEGO, claymation...), Couples, Indian Styles, Photography, Cartoon & Anime, Art & Painting, Retro & Vintage, Sci-Fi & Future, Fantasy & History, Fashion & Product, Fun & Novelty, and Camera Effects.
- **Search** across titles, prompt text, and tags.
- **Category picker** — a tap-to-select grid sheet instead of a fiddly horizontal scroll.
- **Tags** on every prompt for quick scanning.
- **One-tap copy** to clipboard with a confirmation snackbar.
- **Share to AI apps only** — the share sheet is scoped to installed AI companion apps (ChatGPT, Gemini, Claude, Copilot, Perplexity, DeepSeek) instead of the full system share sheet.
- **Favorites** — heart any prompt, persisted locally, with a favorites-only filter.
- Native splash screen and custom adaptive app icon.

## Tech stack

- Kotlin, Jetpack Compose, Material 3
- AGP 9.4.0's built-in Kotlin compilation (no separate Kotlin Gradle plugin — see note below)
- `androidx.core:core-splashscreen` for the launch splash screen
- No backend — prompts are bundled locally in `PromptRepository`

## Project structure

```
app/src/main/java/com/example/fotoprompts/
├── MainActivity.kt              # Entry point, installs the splash screen
├── model/PromptItem.kt          # PromptItem data class + PromptCategory enum
├── data/PromptRepository.kt     # The curated list of prompts
└── ui/
    ├── PromptLibraryScreen.kt   # Main screen: search, category sheet, cards, share/copy
    └── theme/                   # Material 3 theme (colors, typography)
```

## Build & run

```
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

Requires JDK 11+ and the Android SDK (`local.properties` should point `sdk.dir` at your SDK install). Minimum SDK 24, target/compile SDK 37.

### Note on AGP 9's built-in Kotlin support

This project relies on AGP 9.4.0's built-in Kotlin compilation rather than the classic `org.jetbrains.kotlin.android` Gradle plugin, since that plugin is incompatible with AGP 9's new DSL. Only `org.jetbrains.kotlin.plugin.compose` is applied for the Compose compiler.

## Adding a new prompt

Add a `PromptItem(id, title, category, prompt, tags)` entry to `PromptRepository.kt`. Pick an existing `PromptCategory` or add a new one in `PromptItem.kt` (remember to also give it an accent color in `CategoryAccents` in `PromptLibraryScreen.kt`).
