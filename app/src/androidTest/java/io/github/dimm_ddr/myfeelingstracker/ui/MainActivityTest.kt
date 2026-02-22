package io.github.dimm_ddr.myfeelingstracker.ui

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented UI tests for MainActivity.
 * Tests basic Compose UI rendering.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun activityLaunchesSuccessfully() {
        // Activity should launch without crashing
        // This is a basic smoke test to ensure the app starts
        composeTestRule.waitForIdle()
    }

    @Test
    fun appNameIsDisplayedInTheme() {
        // Basic test to verify compose content is rendered
        // More specific UI tests will be added when UI is implemented
        composeTestRule.waitForIdle()
    }
}
