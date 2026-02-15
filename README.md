# My Feelings Tracker

Simple Android app to track daily emotions through Plutchik's wheel of emotions.

## Overview

My Feelings Tracker is an Android application built with **Kotlin** and **Jetpack Compose** that helps users track and understand their emotional patterns. The app uses Plutchik's wheel of emotions as a framework for categorizing feelings.

## Features

- 📊 Track emotions with intensity levels (1-10 scale)
- 📝 Add notes to emotion logs
- 🎨 Modern Material 3 design
- 💾 Local storage with Room database
- 🔄 Reactive UI with Kotlin Flow

## Technology Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose with Material 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room (SQLite)
- **Async**: Kotlin Coroutines & Flow
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
│   │   ├── data/           # Data layer
│   │   │   ├── database/   # Room database (Entity, DAO, Database)
│   │   │   └── repository/ # Repository pattern
│   │   └── ui/             # UI layer
│   │       ├── viewmodels/ # ViewModels
│   │       ├── screens/    # Composable screens
│   │       ├── theme/      # Material 3 theme
│   │       └── MainActivity.kt
│   └── res/                # Resources
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
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## About Plutchik's Wheel of Emotions

The app is based on Robert Plutchik's theory of emotions, which organizes emotions into eight primary emotion dimensions:
- Joy ↔ Sadness
- Trust ↔ Disgust
- Fear ↔ Anger
- Surprise ↔ Anticipation

These combine to form more complex emotional states, providing a comprehensive framework for emotional tracking.

