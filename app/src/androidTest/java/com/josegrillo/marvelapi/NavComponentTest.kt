package com.josegrillo.marvelapi

import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.josegrillo.marvelapi.ui.characterlist.CharacterListFragment
import com.josegrillo.marvelapi.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull

@LargeTest
@RunWith(AndroidJUnit4::class)
class NavComponentTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun detailActivityNavigationTest() {

        val charactersListScenario =
            FragmentScenario.launchInContainer(CharacterListFragment::class.java, null, null)

        charactersListScenario.onFragment { fragment ->

            assertNotNull(fragment.activity);
            val navController = TestNavHostController(
                fragment.requireActivity()
            )
            fragment.activity?.runOnUiThread { navController.setGraph(R.navigation.marvel_nav) }
            Navigation.setViewNavController(fragment.requireView(), navController)

            navController.navigate(R.id.action_characterListFragment_to_detailFragment)
            val destination = navController.currentDestination
            assertNotNull(destination)
            assertEquals(destination!!.id, R.id.detailFragment)
        }
    }
}
