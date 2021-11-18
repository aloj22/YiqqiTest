package com.yiqqi.beers

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import com.yiqqi.beers.ui.MainActivity
import com.yiqqi.beers.ui.list.BeerAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsNot.not
import org.junit.Rule
import org.junit.Test


class SetAvailabilityTest {


    @get:Rule
    var activityRule = activityScenarioRule<MainActivity>()


    @Test
    fun setAvailability() {

        Thread.sleep(1000)

        // Check not available not visible in list
        onView(withIndex(withId(R.id.unavailable_text), 0)).check(matches(not(isDisplayed())))
        onView(withId(R.id.beers_recycler_view)).perform(actionOnItemAtPosition<BeerAdapter.BeerViewHolder>(0, click()))


        //Check not available not visible in details and set unavailable
        onView(withId(R.id.unavailable_text)).check(matches(withAlpha(0f)))
        onView(withId(R.id.availability_button)).perform(click())
        onView(withId(R.id.unavailable_text)).check(matches(withAlpha(1f)))

        pressBack()

        // Check not available visible in list
        onView(withIndex(withId(R.id.unavailable_text), 0)).check(matches(isDisplayed()))
        onView(withId(R.id.beers_recycler_view)).perform(actionOnItemAtPosition<BeerAdapter.BeerViewHolder>(0, click()))


        //Check not available visible in details and set available
        onView(withId(R.id.unavailable_text)).check(matches(withAlpha(1f)))
        onView(withId(R.id.availability_button)).perform(click())
        onView(withId(R.id.unavailable_text)).check(matches(withAlpha(0f)))

    }

    private fun withIndex(matcher: Matcher<View>, @Suppress("SameParameterValue") index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            private var currentIndex = 0

            override fun describeTo(description: Description?) {
                description?.appendText("with index: ")
                description?.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View?): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }

}