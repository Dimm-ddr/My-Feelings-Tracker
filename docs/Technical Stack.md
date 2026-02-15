# Emotion Tracker App - Technical Stack

## Overview

This document specifies the technologies, frameworks, and libraries used to build the Emotion Tracker Android application. All choices prioritize simplicity, modern best practices, and maintainability for a single-developer project.

---

## Development Environment

### IDE
**Android Studio** (Latest Stable)
- Official Android development IDE from Google
- Includes Android SDK, emulator, build tools
- Kotlin support built-in
- Jetpack Compose preview and debugging tools
- Free and open source

**System Requirements:**
- Windows 10/11, macOS, or Linux
- 8GB RAM minimum (16GB recommended)
- 10GB+ free disk space

### Version Control
**Git** (optional but recommended)
- Track code changes
- Enable experimentation with branches
- Facilitate backups of code

---

## Core Technologies

### Programming Language

**Kotlin** (Latest Stable)

**Why Kotlin:**
- Official language for Android development (Google's recommendation)
- Modern, concise syntax with less boilerplate than Java
- Null safety built into the language (fewer crashes)
- Excellent Android Studio integration
- Large, active community with abundant resources
- Coroutines for async operations (clean, readable)
- Aligns with modern Python syntax preferences (type inference, data classes, etc.)

**Version:** Target latest stable release at time of development (1.9.x or newer)

**Learning Resources:**
- [Official Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Kotlin for Android Developers](https://developer.android.com/kotlin)
- [Kotlin Bootcamp Course (Google)](https://developer.android.com/courses/kotlin-bootcamp/overview)

---

### UI Framework

**Jetpack Compose** (Latest Stable)

**What it is:**
- Modern, declarative UI toolkit for Android
- Build UI entirely in Kotlin (no XML layouts)
- Similar to React, SwiftUI, or Flutter in approach
- Official recommendation from Google for new apps

**Why Compose:**
- Significantly less code than traditional XML views
- Easier to build dynamic UIs
- State management integrated elegantly
- Hot reload-like capabilities (preview updates live)
- Native performance
- Future-proof (Google's active development focus)
- Perfect for simple, single-purpose apps

**Version:** Compose BOM (Bill of Materials) for version management

**Core Compose Libraries:**
- `androidx.compose.ui` - Core UI primitives
- `androidx.compose.material3` - Material Design 3 components
- `androidx.compose.foundation` - Foundation layer (layout, gestures)
- `androidx.activity:activity-compose` - Activity integration
- `androidx.navigation:navigation-compose` - Navigation between screens

**Learning Resources:**
- [Jetpack Compose Tutorial (Google)](https://developer.android.com/jetpack/compose/tutorial)
- [Compose Pathway (Google)](https://developer.android.com/courses/pathways/compose)
- [Thinking in Compose](https://developer.android.com/jetpack/compose/mental-model)

**Example - Simple UI Code:**
```kotlin
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("How are you feeling?")
        EmotionWheel(onEmotionSelected = { emotion -> 
            // Handle selection
        })
    }
}
```

---

## Data Layer

### Local Database

**Room** (Latest Stable)

**What it is:**
- Abstraction layer over SQLite
- Part of Android Jetpack (official Google libraries)
- Provides compile-time verification of SQL queries
- Type-safe database access

**Why Room:**
- Simple to use with minimal boilerplate
- Prevents SQL errors at compile time
- Excellent integration with Kotlin coroutines
- Official recommendation for local data storage
- Perfect for structured data like emotion logs

**Components:**
- `@Entity` - Data classes representing tables
- `@Dao` - Data Access Objects (query interfaces)
- `@Database` - Database holder class

**Dependencies:**
- `androidx.room:room-runtime`
- `androidx.room:room-ktx` (Kotlin extensions + coroutines)
- `androidx.room:room-compiler` (annotation processor)

**Example - Entity Definition:**
```kotlin
@Entity(tableName = "emotion_logs")
data class EmotionLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val emotionCategory: String,
    val intensity: String,
    val timestamp: Long,
    val lastModified: Long? = null
)
```

**Learning Resources:**
- [Room Documentation](https://developer.android.com/training/data-storage/room)
- [Room with Kotlin Flows](https://developer.android.com/codelabs/android-room-with-a-view-kotlin)

---

### Settings Storage

**DataStore** (Preferences)

**What it is:**
- Modern replacement for SharedPreferences
- Type-safe key-value storage for app settings
- Asynchronous API using Kotlin coroutines/Flow
- Part of Android Jetpack

**Why DataStore:**
- Better performance than SharedPreferences
- Handles errors gracefully
- Thread-safe
- Works well with Compose state management
- Official recommendation for settings storage

**Use Cases:**
- Store alarm times
- Theme/color scheme preference
- Language selection
- Toggle settings (haptic feedback, auto-close, etc.)

**Dependencies:**
- `androidx.datastore:datastore-preferences`

**Learning Resources:**
- [DataStore Documentation](https://developer.android.com/topic/libraries/architecture/datastore)

---

## Notifications & Scheduling

### Alarm/Reminder System

**WorkManager** (Latest Stable)

**What it is:**
- Android Jetpack library for deferrable, guaranteed background work
- Handles task scheduling with system-aware constraints
- Survives app process death and device reboot

**Why WorkManager:**
- More reliable than AlarmManager for recurring tasks
- Automatically handles battery optimization considerations
- Works with Doze mode and App Standby
- Backwards compatible to API 14
- Official recommendation for background tasks

**Use Cases:**
- Schedule daily emotion check-in reminders
- Trigger notifications at user-configured times
- Persist across device reboots

**Alternative Considered:**
- AlarmManager (older, more manual setup)
- WorkManager preferred for its reliability and system integration

**Dependencies:**
- `androidx.work:work-runtime-ktx`

**Learning Resources:**
- [WorkManager Documentation](https://developer.android.com/topic/libraries/architecture/workmanager)
- [Schedule Tasks with WorkManager](https://developer.android.com/codelabs/android-workmanager)

---

## Image Handling

### Image Loading

**Coil** (Compose-Native Image Loader)

**What it is:**
- Modern image loading library for Android
- Built specifically for Kotlin and Jetpack Compose
- Lightweight and performant

**Why Coil:**
- Native Compose integration (`AsyncImage` composable)
- Kotlin-first design
- Smaller footprint than alternatives (Glide, Picasso)
- Simple API
- Handles caching automatically

**Use Cases:**
- Load and display emotion wheel image
- Handle different screen densities
- Cache loaded image for performance

**Dependencies:**
- `io.coil-kt:coil-compose`

**Example:**
```kotlin
AsyncImage(
    model = R.drawable.emotion_wheel,
    contentDescription = "Plutchik emotion wheel",
    modifier = Modifier.size(300.dp)
)
```

**Alternative:**
- Could use native Compose `Image` for simple cases
- Coil adds caching and advanced features if needed

**Learning Resources:**
- [Coil Documentation](https://coil-kt.github.io/coil/)

---

## Additional Libraries

### Date/Time Handling

**Kotlinx-datetime** (Optional)

**What it is:**
- Multiplatform Kotlin library for date/time operations
- Cleaner API than Java's Date/Calendar

**Why Consider:**
- Simplifies timestamp handling
- Timezone-aware operations
- Modern Kotlin API

**Alternative:**
- Use Java's `Instant`, `LocalDateTime` (available in API 26+)
- Keep dependencies minimal - may not need extra library

**Decision:** Evaluate during development; standard library may suffice.

---

### CSV Export

**No External Library Needed**

Generate CSV manually with Kotlin string operations:
- Simple format (comma-separated values)
- Full control over output
- No dependency overhead

**Example:**
```kotlin
fun exportToCsv(logs: List<EmotionLog>): String {
    val header = "Date,Time,Emotion,Intensity\n"
    val rows = logs.joinToString("\n") { log ->
        "${log.date},${log.time},${log.emotion},${log.intensity}"
    }
    return header + rows
}
```

---

### PDF Export (Optional)

**iText** or **PDFDocument (Android API)**

**If implementing PDF export:**
- `PDFDocument` (built into Android) - free, simple, sufficient for basic formatting
- iText - more powerful but larger library and licensing considerations

**Recommendation:** Start with plain text or CSV export; add PDF later if needed.

---

## Architecture Pattern

### Recommended Structure

**MVVM (Model-View-ViewModel)**

**Components:**
- **Model**: Room database entities and DAOs
- **View**: Compose UI screens
- **ViewModel**: Holds UI state, interacts with data layer
- **Repository**: Optional abstraction layer between ViewModel and data sources

**Why MVVM:**
- Official Android recommendation
- Clean separation of concerns
- Testable business logic
- Compose works naturally with ViewModels
- Survives configuration changes (screen rotation)

**Dependencies:**
- `androidx.lifecycle:lifecycle-viewmodel-compose`
- `androidx.lifecycle:lifecycle-runtime-compose`

**Example Structure:**
```
app/
├── data/
│   ├── EmotionLog.kt (entity)
│   ├── EmotionDao.kt (data access)
│   └── EmotionDatabase.kt
├── ui/
│   ├── MainScreen.kt (composable)
│   ├── CalendarScreen.kt
│   └── SettingsScreen.kt
└── viewmodel/
    ├── EmotionViewModel.kt
    └── SettingsViewModel.kt
```

---

## Build System

### Gradle

**Gradle with Kotlin DSL** (build.gradle.kts)

**What it is:**
- Build automation tool for Android
- Manages dependencies, compilation, and packaging

**Why Kotlin DSL:**
- Type-safe build scripts
- Better IDE support
- Consistent with Kotlin language choice

**Key Gradle Plugins:**
- Android Application plugin
- Kotlin Android plugin
- Room annotation processor (KSP)

**Example Dependencies Block:**
```kotlin
dependencies {
    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.02.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    
    // Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    
    // WorkManager
    implementation("androidx.work:work-runtime-ktx:2.9.0")
    
    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")
}
```

---

## Testing (Optional but Recommended)

### Unit Testing

**JUnit 4/5** + **Kotlin Test**
- Test business logic and ViewModels
- No Android framework needed

### UI Testing

**Compose Testing**
- Test UI interactions
- Verify navigation and state changes

**Dependencies:**
- `androidx.compose.ui:ui-test-junit4`
- `androidx.test:runner`
- `androidx.test:rules`

**Note:** Testing is optional for initial development but valuable for reliability.

---

## Android Manifest Configuration

### Required Permissions

```xml
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
```

### Auto Backup Configuration

```xml
<application
    android:allowBackup="true"
    android:fullBackupContent="@xml/backup_rules"
    ...>
</application>
```

**Backup Rules (`res/xml/backup_rules.xml`):**
- Include database files
- Exclude cache and temporary files

---

## Development Workflow

### Setup Steps

1. **Install Android Studio**
   - Download latest stable version
   - Install Android SDK (API 26-34)
   - Configure emulator or connect physical device

2. **Create New Project**
   - Template: "Empty Compose Activity"
   - Language: Kotlin
   - Minimum SDK: API 26 (Android 8.0)
   - Build configuration: Kotlin DSL

3. **Add Dependencies**
   - Update `build.gradle.kts` with required libraries
   - Sync project

4. **Configure Room**
   - Create entity classes
   - Define DAOs
   - Build database class

5. **Build UI with Compose**
   - Create main screen composables
   - Implement navigation
   - Connect to ViewModels

6. **Implement Features**
   - Logging flow
   - Calendar view
   - Settings screen
   - Alarms with WorkManager

7. **Test on Device**
   - Use physical device (recommended for alarm testing)
   - Emulator for general UI testing

8. **Prepare Release**
   - Generate signing key
   - Configure release build
   - Enable ProGuard/R8 for code shrinking
   - Build release AAB (Android App Bundle)

---

## Useful Tools

### Development

- **Android Debug Bridge (adb)** - Command-line tool for device interaction
- **Layout Inspector** - Debug Compose UI hierarchy
- **Database Inspector** - View Room database contents in real-time

### Design

- **Material Theme Builder** - Generate color schemes for Material 3
- **Figma** or **Inkscape** - Edit emotion wheel image if needed

### Version Control

- **GitHub Desktop** or **Git CLI** - Source control

---

## Learning Path Recommendation

### Phase 1: Kotlin Basics (1-2 weeks)
- Basic syntax, null safety, data classes
- Functions, lambdas, higher-order functions
- Coroutines fundamentals

### Phase 2: Jetpack Compose Basics (1-2 weeks)
- Composable functions
- State management
- Layout composables (Column, Row, Box)
- Material 3 components

### Phase 3: Android Specifics (1 week)
- Activity lifecycle
- Navigation
- Permissions

### Phase 4: Data Layer (1 week)
- Room database setup
- DAOs and queries
- ViewModels

### Phase 5: App Development (2-4 weeks)
- Implement emotion logging
- Build calendar view
- Settings and alarms
- Polish and testing

**Total Estimate:** 6-10 weeks for complete app

---

## References & Documentation

### Official Android Documentation
- [Android Developers](https://developer.android.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Kotlin for Android](https://developer.android.com/kotlin)

### Community Resources
- [Stack Overflow - Android Tag](https://stackoverflow.com/questions/tagged/android)
- [Reddit r/androiddev](https://reddit.com/r/androiddev)
- [Kotlin Slack Community](https://kotlinlang.org/community/)

### Code Examples
- [Android Samples (GitHub)](https://github.com/android)
- [Compose Samples](https://github.com/android/compose-samples)

---

*Document created: 2026-02-14*
*Last updated: 2026-02-14*
