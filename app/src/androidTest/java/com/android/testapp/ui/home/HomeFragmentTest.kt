package com.android.testapp.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.android.testapp.R
import com.android.testapp.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeFragmentTest {
    @Rule
    @JvmField
    public var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun ensureTextChangesWork() {
        // Type text and then press the button.
        onView(withId(R.id.etInput))
            .perform(typeText("New News"), closeSoftKeyboard())
        onView(withId(R.id.btReset)).perform(click())

        // Check that the text was changed.
        onView(withId(R.id.etInput)).check(matches(withText("Top News")))
    }
}