# Emotion Tracker App - Functional Specification

## Overview

A minimalist emotion tracking application that allows users to quickly log their emotional state multiple times per day using the Plutchik emotion wheel model. The app prioritizes speed and simplicity, enabling users to record emotions in 2 taps without journaling or complex workflows.

---

## Core Functionality

### Emotion Model

The app uses **Plutchik's Wheel of Emotions** consisting of:
- **8 primary emotion categories**: Joy, Sadness, Anger, Fear, Trust, Disgust, Anticipation, Surprise
- **3 intensity levels per category**:
  - Mild (outer ring)
  - Moderate (middle ring)
  - Intense (inner ring)
- **Total: 24 distinct emotional states**

### Primary User Flow

**Two-tap logging system:**

**Step 1: Select Emotion Category**
- User sees emotion wheel with 8 petals
- Each petal shows a **single representative label** for that emotion family (e.g., "Joy" represents the joy/serenity/ecstasy family)
- User taps on one of the 8 petals

**Step 2: Select Intensity**
- The selected petal section expands or opens detail view
- Display shows:
  - The three intensity levels for the selected emotion (large, readable text/buttons)
  - Optionally: the two adjacent combined emotions from the outer ring (for context/reference)
- User taps their specific intensity level
- Emotion is logged with current timestamp
- Visual confirmation is shown (checkmark, toast message, or haptic feedback)
- The app returns to main screen (or closes, if configured)

**Expected completion time**: 2-3 seconds per log entry

**Note on Implementation Details:**
The exact presentation of the expanded view (overlay, modal, or transformation) and inclusion of outer ring emotions will be determined during development based on visual testing on actual device.

---

## User Interface

### Main Screen

**Components:**
- Emotion wheel image (Plutchik wheel) occupying majority of screen (~85-90% of available width)
- Each of the 8 main petals labeled with a single representative emotion name
- Wheel is interactive and responds to touch
- Minimal chrome - no unnecessary UI elements during logging

**Visual Design:**
- Clear visual separation between the 8 petal sections
- Labels positioned for readability
- Touch-responsive feedback (highlight, ripple effect)

### Expanded Emotion Selection

**Triggered by:** Tapping one of the 8 main petals

**Display:**
- **Primary content**: Three intensity options for selected emotion, shown as large tappable elements:
  - Mild intensity (outer) - with specific emotion name
  - Moderate intensity (middle) - with specific emotion name
  - Intense intensity (inner) - with specific emotion name
  
**Example for Joy petal:**
- Serenity (mild)
- Joy (moderate)
- Ecstasy (intense)

**Optional context**: 
- May include the two adjacent combined emotions from outer ring
- Implementation to be determined based on visual testing and screen space

**Interaction:**
- Single tap on intensity level logs the emotion
- Back button or tap outside returns to main wheel
- Visual feedback on tap

### Calendar View

**Purpose**: Review past emotion logs by date

**Features:**
- Month view showing days with logged emotions
- Visual indicators on days with logs:
  - Dot, color coding, or count badge
  - Multi-log days clearly indicated
- Tap on any date to see that day's emotion entries
- Navigation between months (swipe or arrows)

**Day Detail View:**
- List of all emotions logged on selected day
- Each entry shows:
  - Emotion name and intensity
  - Timestamp (time of day)
  - Color-coded by emotion family
  - Edit and delete action buttons
- Sorted chronologically (earliest to latest)

### History/List View

**Purpose**: Chronological view of all logged emotions

**Features:**
- Scrollable list of emotion entries, newest first
- Each entry displays:
  - Date and time
  - Emotion name and intensity
  - Visual indicator (color, icon) by emotion family
- Tap on entry to edit or delete
- Optional: Search/filter capabilities

**Empty State:**
- When no logs exist: "No emotions logged yet. Tap the wheel to start."

### Settings Screen

**Access:** Via settings icon/button (not on main screen)

**Configuration Categories:**

**Alarms & Reminders:**
- Enable/disable daily reminders
- Add/edit/remove multiple alarm times
- Configure alarm behavior:
  - Sound selection
  - Vibration on/off
  - Notification style

**Display:**
- Color scheme / theme selection
  - Light mode
  - Dark mode
  - Optional: additional color variants
- Language selection (minimum: English, Russian)

**Data Management:**
- Export data (opens file sharing)
- Import data (opens file picker)
- Clear all data (with confirmation dialog)
- View automatic backup status

**Behavior:**
- Auto-close app after logging (toggle)
- Haptic feedback (toggle)
- Confirmation prompts (toggle)

**About:**
- App version number
- Attribution for emotion wheel source image
- License information (for wheel image)
- Link to scientific references (optional)

---

## Data Management

### Data Model

**Emotion Log Entry:**
- Unique identifier (UUID or auto-increment ID)
- Emotion category (one of 8 primary emotions)
- Intensity level (mild/moderate/intense)
- Timestamp (date + time, stored in UTC)
- Created date (for tracking)
- Last modified date (null if never edited)

**Technical note:** Exact database schema determined during implementation.

### Storage

- All data stored locally on device
- No cloud sync or server communication
- No internet connection required
- Database persists across app restarts and system reboots

### Backup & Restore

**Automatic Backup:**
- Utilizes Android's Auto Backup system (enabled by default in AndroidManifest)
- Backs up to user's Google Drive automatically
- Happens daily when device is idle, charging, and on Wi-Fi
- No user action required
- Restores automatically on app reinstall or new device setup
- Database size well within 25MB limit

**Manual Export:**
- User-initiated export creates shareable file
- Intended for sharing with therapists or personal review
- Export formats:
  - **CSV**: Structured data, columns for date/time/emotion/intensity
  - **Formatted text or PDF**: Human-readable report grouped by date
- File naming: Auto-generated with date (e.g., `emotions_2026-02-14.csv`)

**Manual Import:**
- User selects previously exported file
- App parses and validates data
- Duplicate handling:
  - Check for matching timestamps
  - Prompt user: "Entry already exists. Skip or overwrite?"
- Merges with existing data (non-destructive)

**Share Functionality:**
- "Share" button in export menu
- Generates export file and opens Android share sheet
- User selects destination (email, messaging, cloud storage, etc.)
- Leverages native Android sharing mechanism

---

## Features Detail

### Edit Record

**Access Points:**
- Calendar day detail view
- History list view

**Edit Capabilities:**
- Change emotion category (select from 8 options)
- Change intensity level (select from 3 options)
- Modify timestamp (date and time picker)

**User Flow:**
1. Tap on existing log entry
2. Edit screen shows current values
3. Modify desired fields
4. Save or Cancel
5. On Save: Updates `last_modified` timestamp

**Validation:**
- Cannot create duplicate timestamps (warn user if conflict)
- All fields required (cannot save incomplete entry)

### Delete Record

**Access Points:**
- Calendar day detail view
- History list view

**User Flow:**
1. Tap delete button/icon on entry
2. Confirmation dialog: "Delete this entry? This cannot be undone."
3. Confirm or Cancel
4. On Confirm: Permanently removes entry from database

**No undo mechanism** - deletion is permanent.

### Alarms & Notifications

**Purpose:**
- Remind user to log emotions at chosen times throughout day
- Tapping notification opens app ready to log

**Configuration:**
- User sets desired reminder times in Settings
- Multiple reminders per day supported (e.g., 9 AM, 2 PM, 8 PM)
- Each reminder can be individually enabled/disabled

**Notification Behavior:**
- Displays at configured time
- Notification text: "Time to check in - How are you feeling?"
- Tapping notification launches app to main screen
- Dismissing notification without action doesn't log anything

**Technical Requirements:**
- Alarms must survive device reboot (use RECEIVE_BOOT_COMPLETED)
- Must work despite battery optimization (user may need to whitelist app)
- If alarm reliability is compromised by system, inform user in Settings

**Permissions:**
- POST_NOTIFICATIONS (Android 13+)
- SCHEDULE_EXACT_ALARM (if needed for precise timing)

### Duplicate Prevention

**Scenario:** User accidentally taps twice rapidly, or logs same emotion within short time

**Prevention Mechanisms:**

**UI Debouncing:**
- Disable touch input for 500-1000ms after successful log
- Prevents accidental double-taps

**Duplicate Detection:**
- If entry exists with same emotion within 1 minute window, prompt user:
  - "You already logged [Emotion] at [Time]. Log another entry?"
  - Options: "Yes, log anyway" / "Cancel"

### Visual Feedback

**On Successful Log:**
- Brief confirmation display (1-2 seconds):
  - Toast message: "Logged: [Emotion]"
  - Or: Checkmark animation
  - Or: Status text overlay
- Haptic feedback (short vibration) if enabled in Settings
- Optional: Automatic return to main screen after brief delay

**On Touch Interaction:**
- Immediate visual response to all taps:
  - Button press effects
  - Ripple animations
  - Highlights
- No perceived lag or delay

---

## User Experience Requirements

### Speed & Simplicity

- Primary logging flow completable in under 5 seconds
- No mandatory text entry or journaling
- Minimal navigation required
- Advanced features accessible but not intrusive
- Main screen dedicated entirely to logging action

### Reliability

- No crashes or freezes
- All logged data must persist immediately (survive force-close/crash)
- Alarms must be as reliable as Android system permits
- Data never lost or corrupted

### Readability

**Text Sizing:**
- All text readable on 6.1" phone screen (425 PPI) at minimum
- Primary labels: Large and clear
- Secondary information: Smaller but still legible
- Respects user's system font size settings

**Touch Targets:**
- Minimum touch target size: 48dp (Android accessibility guideline)
- Adequate spacing between tappable elements
- Forgiving hit detection (slightly larger than visual boundaries)

### Accessibility

- Support system font scaling
- Color-blind friendly (don't rely solely on color for information)
- Sufficient contrast ratios (WCAG AA minimum)
- Screen reader compatible (proper content descriptions)

### Privacy

- No data transmission over network
- No analytics or tracking libraries
- No user accounts or authentication
- All data local to device only
- Clear privacy messaging in app store listing

---

## Localization

### Supported Languages

**Minimum Launch Support:**
- English (default)
- Russian

**Translated Elements:**
- All 24 emotion state names
- All UI labels and buttons
- Settings screen text
- Confirmation dialogs and prompts
- Error messages
- Empty state messages
- Notification text

**Language Selection:**
- In Settings > Display > Language
- Or automatically use system language if available

**Not Translated:**
- Export file column headers (acceptable to keep in English for CSV)
- Attribution text (can remain in original language)
- Log file/technical strings

---

## Visual Assets

### Emotion Wheel Image

**Source:**
- Use openly licensed Plutchik wheel (CC BY-SA 4.0 or similar)
- English version from Wikimedia Commons or Open Emotion Wheel
- Russian version: translate from licensed source
- Proper attribution included in app (Settings > About)

**Display Requirements:**
- High-resolution image suitable for 425+ PPI displays
- Scales appropriately for different screen sizes (5.5" to 7" range)
- Maintains aspect ratio (approximately square)
- Optimized file size (PNG or similar)

**Modifications:**
- Simplified labels on main wheel (only 8 representative names)
- Outer ring may or may not be visible on main screen (TBD during development)
- Color coding must remain consistent with original Plutchik model

**Touch Interaction:**
- Image divided into 8 tap zones corresponding to petals
- Touch detection accurate and responsive
- Visual feedback on touch (highlight, glow, or overlay effect)

---

## Technical Constraints

### Platform

- **Target:** Android only
- **Minimum SDK:** Android 8.0 (API level 26)
- **Target SDK:** Latest stable at time of development
- **Architecture:** ARM and x86 support (standard Android)

### Size Targets

- **APK/AAB size:** Under 10MB
- **Database size:** Expected <1MB even after years of use (lightweight entries)
- **Memory footprint:** Minimal (simple UI, single activity/few screens)

### Performance

- **App launch:** Ready state in under 2 seconds (cold start)
- **Logging response:** Immediate (<100ms from tap to feedback)
- **Calendar rendering:** Under 500ms for month view
- **History list:** Smooth scrolling (60 FPS minimum)

### Permissions

**Required:**
- `POST_NOTIFICATIONS` (Android 13+) - for alarm notifications
- `RECEIVE_BOOT_COMPLETED` - to restore alarms after reboot

**Conditional:**
- Storage access for manual export (use scoped storage, no broad storage permission)
- `SCHEDULE_EXACT_ALARM` if precise alarm timing is needed (optional)

**Not Used:**
- No internet permission
- No location permission
- No camera/microphone
- No contacts access

### Offline Operation

- App must function **completely offline** at all times
- No network calls under any circumstance
- No error messages related to connectivity
- No "online" features that degrade to "offline mode"

---

## Out of Scope

The following features are **explicitly NOT included** in this version:

- Journaling or notes attached to emotion entries
- Social features or sharing with other users
- Advanced analytics, insights, or trend analysis
- Mood prediction or AI suggestions
- Integration with other health/wellness apps
- Cloud sync across multiple devices (auto backup is sufficient)
- iOS version or cross-platform support
- Therapy or mental health guidance/tips
- Gamification, achievements, or streaks
- Pattern-based reminders (e.g., "remind me when sad")
- Photos or media attached to entries
- Voice logging
- Home screen widgets
- Wear OS support

These may be considered for future iterations but are not part of the initial functional scope.

---

## Success Criteria

The app meets its goals if:

1. **Speed:** User can log an emotion in 2 taps within 3 seconds
2. **Reliability:** All data is safely stored; no data loss reported
3. **Portability:** Export/import successfully migrates data between devices
4. **Usability:** Calendar and history views enable easy review of past logs
5. **Performance:** App feels fast and responsive; no lag or stuttering
6. **Intuitiveness:** Interface is self-explanatory; no tutorial needed
7. **Stability:** No crashes or bugs that prevent core functionality

**User Feedback Goals:**
- "This is exactly what I needed - simple and fast"
- "I actually use it daily because it's so quick"
- "Finally an emotion tracker without all the extra stuff"

---

## Licensing & Distribution

### Source Code License

The app will be released as **open source** under the **MIT License**.

**What this means:**
- Source code publicly available on GitHub
- Anyone can view, modify, and distribute the code
- Commercial use allowed (for anyone)
- No warranty or liability
- Must include original copyright notice and license text

**Rationale:**
- Builds trust for privacy-focused emotion tracking app
- Encourages community contributions and code review
- Provides portfolio value for developer
- Maintains flexibility for future monetization
- Low barrier to replication makes closed source unnecessary

### App Distribution

**Primary Distribution:**
- Google Play Store (free or paid - to be determined)
- GitHub Releases (APK files)
- Optional: F-Droid (privacy-focused users)

**License Attribution Requirements:**
- App must include license text in Settings → About → Licenses
- Splash screen or about page mentions open source status
- Play Store description links to GitHub repository

### Third-Party Assets

**Emotion Wheel Image:**
- Must use openly licensed Plutchik wheel (CC BY-SA 4.0 or similar)
- Attribution required in Settings → About section
- Source and license clearly documented

**Example attribution text:**
```
Emotion wheel based on Plutchik's Wheel of Emotions
Image adapted from [source name], licensed under CC BY-SA 4.0
```

### Monetization Compatibility

**MIT License allows:**
- Charging for app on Play Store (convenience distribution fee)
- Adding proprietary premium features not in open source version
- Offering paid services (custom builds, support, white-label)
- Accepting donations via GitHub Sponsors, Ko-fi, etc.

**User expectations:**
- Core app functionality remains free and open source
- Premium features (if added later) are optional
- No misleading claims about "completely free" if paid options exist

---

*Document created: 2026-02-14*
*Last updated: 2026-02-14*
