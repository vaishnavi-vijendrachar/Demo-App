package com.vaishnavi.telstratest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import com.vaishnavi.telstratest.ui.main.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainInstrumentationTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = getInstrumentation().targetContext
        assertEquals("com.vaishnavi.telstratest", appContext.packageName)
    }

    @Before
    fun launchActivity() {
        ActivityScenario.launch(MainActivity::class.java)
        //delay until the data is fectched
        Thread.sleep(3000)
    }

    @Test
    fun scrollToItemBelowFold_checkItOpens() {
        // test if the list clickable
        onView(withId(R.id.recyclerView)).perform(click())
    }

    @Test
    fun scrollToItemBelowFold_checkItOpens2() {
        // check if list is displayed
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun scrollToPosition() {
        //scroll to position
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    7, scrollTo()
                )
            )
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1, scrollTo()
                )
            )
    }

    @Test
    fun scroll_clickAtPosition() {
        //scroll and click
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    7, click()
                )
            )
    }

    @Test
    fun test_checkTextPresent() {
        onView(withId(R.id.toolbar_title))
            .check(matches(withText("About Canada")))
    }

    @Test
    fun test_swipeDown() {
        onView(withId(R.id.swipe)).perform(swipeDown())
    }

    @Test
    fun click_backPress() {
        val mDevice =
            UiDevice.getInstance(getInstrumentation())
        mDevice.pressBack()
    }

    @Test
    fun orientation_change() {
        val device = UiDevice.getInstance(getInstrumentation())
        device.setOrientationLeft()
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        device.setOrientationNatural()
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        device.setOrientationRight()
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        device.setOrientationNatural()
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun itemDisplayed_backPress() {
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        UiDevice.getInstance(getInstrumentation()).pressBack()
    }
}
