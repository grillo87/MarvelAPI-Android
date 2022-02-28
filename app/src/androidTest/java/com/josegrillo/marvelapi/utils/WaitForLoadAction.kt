package com.josegrillo.marvelapi.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Matcher

class WaitForLoadAction(
    private val desiredPosition: Int,
    private val timeout: Long
) : ViewAction {

    override fun getConstraints(): Matcher<View> {
        return isAssignableFrom(RecyclerView::class.java)
    }

    override fun getDescription(): String {
        return "wait up to $timeout milliseconds for the view to have position $desiredPosition"
    }

    override fun perform(uiController: UiController, view: View) {
        val endTime = System.currentTimeMillis() + timeout
        do {
            uiController.loopMainThreadForAtLeast(50)
        } while (System.currentTimeMillis() < endTime)
    }
}