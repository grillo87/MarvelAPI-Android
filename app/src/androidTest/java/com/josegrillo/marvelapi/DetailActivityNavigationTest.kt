package com.josegrillo.marvelapi

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.josegrillo.marvelapi.ui.main.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.josegrillo.marvelapi.utils.WaitForLoadAction

@LargeTest
@RunWith(AndroidJUnit4::class)
class DetailActivityNavigationTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)
    private val desiredClickActionsIndex = listOf(3, 5)

    @Test
    fun detailActivityNavigationTest() {

        val recyclerView = onView(
            withId(R.id.main_activity_characters_list)
        ).perform(WaitForLoadAction())
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(desiredClickActionsIndex[0], click()))

        onView(
            allOf(
                childAtPosition(
                    allOf(
                        withId(R.id.activity_detail_toolbar),
                        childAtPosition(
                            withClassName(`is`("android.widget.RelativeLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        ).perform(click())

        recyclerView.perform(WaitForLoadAction())
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(desiredClickActionsIndex[1], click()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
