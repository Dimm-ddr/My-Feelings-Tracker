# My Feelings Tracker

Simple Android app to track daily emotions through Plutchik's wheel of emotions.

## Overview

My Feelings Tracker is an Android application built with **Kotlin** and **Jetpack Compose** that helps users track and understand their emotional patterns. The app uses Plutchik's wheel of emotions as a framework for categorizing feelings.

## Features

### Current (Foundation)
- 🎨 Modern Material 3 design with Jetpack Compose
- 💾 Local Room database with Plutchik's emotion model (8 categories × 3 intensities)
- 🔄 Reactive UI with Kotlin Flow and StateFlow
- 🌍 Localization support (English and Russian)
- 🔒 Auto backup configuration for data safety
- ✅ Testing infrastructure (unit and instrumented tests)
- 🔔 Notification permissions and boot receiver setup

### Planned
- 📊 Quick 2-tap emotion logging
- 📅 Calendar view for reviewing past emotions
- 📜 Chronological history list
- ⚙️ Settings (display, alarms, data management)
- ⏰ Daily reminders with WorkManager
- 📤 Export/import data (CSV and PDF)
- 🎨 Multiple color schemes

## Technology Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose with Material 3 + Navigation
- **Architecture**: MVVM (Model-View-ViewModel) + Repository pattern
- **Database**: Room (SQLite) with TypeConverters
- **Async**: Kotlin Coroutines & Flow
- **Image Loading**: Coil for Compose
- **Background Tasks**: WorkManager
- **Preferences**: DataStore
- **Testing**: JUnit, Truth, Compose UI Test, Espresso
- **Build**: Gradle with Kotlin DSL

## Architecture

The app follows the official Android architecture guidelines with a clean, layered structure:

- **UI Layer**: ViewModels + Composables organized by feature
- **Data Layer**: Repository pattern + Room database + DataStore

For detailed architecture documentation, see [ARCHITECTURE.md](ARCHITECTURE.md).

## Project Structure

```
app/
├── src/main/
│   ├── java/com/example/myfeelingstracker/
│   │   ├── data/
│   │   │   ├── model/      # Emotion enums (8 categories, 3 intensities)
│   │   │   ├── database/   # Room (Entity, DAO, Database, Converters)
│   │   │   └── repository/ # Repository pattern
│   │   ├── ui/
│   │   │   ├── viewmodels/ # MVVM ViewModels
│   │   │   ├── screens/    # Composable screens
│   │   │   ├── theme/      # Material 3 theme
│   │   │   └── MainActivity.kt
│   │   └── receivers/      # BootReceiver for alarm restoration
│   ├── res/
│   │   ├── values/         # English strings
│   │   ├── values-ru/      # Russian strings
│   │   └── xml/            # Backup rules
│   └── AndroidManifest.xml
├── src/test/               # Unit tests
└── src/androidTest/        # Instrumented tests
```

## Requirements

- Android Studio (latest stable)
- JDK 17 or higher
- Android SDK API 34
- Min Android version: 8.0 (API 26)

## Building

```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## About Plutchik's Wheel of Emotions

The app is based on Robert Plutchik's psychoevolutionary theory of emotions, which organizes emotions into eight primary emotion categories, each with three intensity levels:

**The 8 Primary Emotions (with intensity levels):**
- **Joy**: Serenity → Joy → Ecstasy
- **Sadness**: Pensiveness → Sadness → Grief
- **Anger**: Annoyance → Anger → Rage
- **Fear**: Apprehension → Fear → Terror
- **Trust**: Acceptance → Trust → Admiration
- **Disgust**: Boredom → Disgust → Loathing
- **Anticipation**: Interest → Anticipation → Vigilance
- **Surprise**: Distraction → Surprise → Amazement

This creates **24 distinct emotional states** (8 categories × 3 intensities) that provide a scientifically grounded framework for emotional tracking and self-awareness.

For detailed information about the theory and its applications, see [docs/Project Overview.md](docs/Project%20Overview.md).

