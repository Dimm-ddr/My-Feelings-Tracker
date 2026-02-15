# My Feelings Tracker - Android Architecture

## Overview
This is an Android emotion tracker app built with Kotlin and Jetpack Compose, following official Android architecture guidelines. The app uses Plutchik's wheel of emotions to help users track their daily emotional states.

## Architecture

### Single Module Structure
The app follows a standard layered architecture within a single module:

```
app/
├── src/main/
│   ├── java/com/example/myfeelingstracker/
│   │   ├── data/           # Data layer
│   │   │   ├── database/   # Room database components
│   │   │   │   ├── EmotionLog.kt        # Entity
│   │   │   │   ├── EmotionDao.kt        # Data Access Object
│   │   │   │   └── EmotionDatabase.kt   # Room Database
│   │   │   └── repository/ # Repository pattern
│   │   │       └── EmotionRepository.kt # Data repository
│   │   └── ui/            # UI layer
│   │       ├── viewmodels/ # ViewModels
│   │       │   └── MainViewModel.kt     # Main ViewModel (MVVM)
│   │       ├── screens/    # Composable screens
│   │       │   └── MainScreen.kt        # Main screen composable
│   │       ├── theme/      # UI theme
│   │       │   └── Theme.kt             # Material 3 theme
│   │       └── MainActivity.kt          # Main Activity
│   ├── res/               # Resources
│   └── AndroidManifest.xml
└── build.gradle.kts       # App-level build configuration
```

## Technology Stack

### Core Technologies
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Build System**: Gradle with Kotlin DSL (build.gradle.kts)
- **Architecture Pattern**: MVVM (Model-View-ViewModel)

### Key Libraries

#### Jetpack Components
- **Compose**: Modern declarative UI toolkit
  - Material 3 for design system
  - UI, Graphics, and Tooling
- **Room**: Local database with SQLite
  - Runtime, KTX extensions, KSP compiler
- **DataStore**: Key-value storage (Preferences DataStore)
- **WorkManager**: Background task scheduling
- **ViewModel**: Lifecycle-aware UI data management
- **Lifecycle**: Lifecycle-aware components with Kotlin coroutines

#### Dependencies
- **Coroutines**: Asynchronous programming
- **KSP**: Kotlin Symbol Processing for annotation processing

## Layers

### Data Layer
**Location**: `app/src/main/java/com/example/myfeelingstracker/data/`

Components:
1. **EmotionLog Entity** (`database/EmotionLog.kt`)
   - Represents an emotion log entry in the database
   - Fields: id, emotion, intensity (1-10), timestamp, note
   
2. **EmotionDao** (`database/EmotionDao.kt`)
   - Data Access Object for database operations
   - Methods: getAllEmotions, insertEmotion, updateEmotion, deleteEmotion
   
3. **EmotionDatabase** (`database/EmotionDatabase.kt`)
   - Room database singleton
   - Provides access to DAO
   
4. **EmotionRepository** (`repository/EmotionRepository.kt`)
   - Repository pattern implementation
   - Clean API for data access
   - Mediates between ViewModel and data sources

### UI Layer
**Location**: `app/src/main/java/com/example/myfeelingstracker/ui/`

Components:
1. **MainActivity** (`MainActivity.kt`)
   - Entry point of the app
   - Empty Compose Activity using `setContent`
   - Applies Material 3 theme
   
2. **MainViewModel** (`viewmodels/MainViewModel.kt`)
   - MVVM pattern ViewModel
   - Manages UI state and business logic
   - Exposes StateFlow for emotion list
   - Operations: insert, update, delete emotions
   
3. **MainScreen** (`screens/MainScreen.kt`)
   - Main composable screen
   - Displays emotion tracker UI
   - Minimal implementation (incremental feature development)
   
4. **Theme** (`theme/Theme.kt`)
   - Material 3 theme configuration
   - Light and dark color schemes
   - Follows Material Design guidelines

## Build Configuration

### Project-level (`build.gradle.kts`)
- Android Gradle Plugin: 8.2.2
- Kotlin: 1.9.22
- KSP: 1.9.22-1.0.17

### App-level (`app/build.gradle.kts`)
- Min SDK: 26 (Android 8.0 Oreo)
- Target SDK: 34 (Android 14)
- Compile SDK: 34
- Java Version: 17
- Kotlin JVM Target: 17
- Compose Compiler Extension: 1.5.8

## Design Patterns

### MVVM (Model-View-ViewModel)
- **Model**: Data layer (Room database, Repository)
- **View**: Composable functions (MainScreen)
- **ViewModel**: MainViewModel (manages UI state and business logic)

### Repository Pattern
- EmotionRepository provides a clean API for data operations
- Abstracts data sources from the ViewModel
- Single source of truth for emotion data

### Dependency Injection
- Manual DI through constructor injection
- Database singleton pattern
- Room and Repository instances created in ViewModel

## Features (Minimal Implementation)

### Current Features
- Empty Compose Activity with Material 3 theme
- Room database setup for emotion logging
- EmotionLog entity with fields for emotion tracking
- Repository pattern for data access
- MainViewModel with StateFlow for reactive UI
- Basic MainScreen composable

### Planned Features (Incremental Development)
- Emotion selection UI based on Plutchik's wheel
- Emotion intensity slider (1-10 scale)
- Emotion history list
- Daily/weekly emotion statistics
- Background reminders using WorkManager
- Data persistence with DataStore for user preferences

## Getting Started

### Prerequisites
- Android Studio (latest stable version)
- JDK 17 or higher
- Android SDK with API 34
- Gradle 8.2

### Building the Project
```bash
./gradlew assembleDebug
```

### Running Tests
```bash
./gradlew test
```

## Code Quality

### Kotlin Best Practices
- Null safety
- Coroutines for asynchronous operations
- Flow for reactive streams
- Data classes for models
- Extension functions where appropriate

### Android Best Practices
- Lifecycle-aware components
- Single Activity architecture
- Jetpack Compose for modern UI
- StateFlow for UI state management
- Repository pattern for data abstraction

## Notes

- This is a minimal implementation following the official Android architecture guidelines
- Features are designed to be built incrementally
- The architecture supports easy testing and maintenance
- All components follow separation of concerns principle
