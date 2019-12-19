package com.example.footballleaguemvp

import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.footballleaguemvp.network.TestIdlingResource
import com.example.footballleaguemvp.ui.searchmatch.SearchActivity
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by ivanaazuka on 2019-12-17.
 * Android Engineer
 */

@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchActivityTest {
    @Rule
    @JvmField var searchActivityRule = ActivityTestRule(SearchActivity::class.java)

    private lateinit var idlingRegistry: IdlingRegistry

    @Before
    fun setup(){
        idlingRegistry = IdlingRegistry.getInstance()
        idlingRegistry.register(TestIdlingResource.idlingResource)
    }

    @Test
    fun typeTextOnSearchView_dataIsAvailable_shouldReturnMatchList_andClickTheItem() {

        onView(withId(R.id.searchViewEdit))
            .check(matches(isDisplayed()))
            .check(matches(isFocusable()))

        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("Arsenal"), pressImeActionButton())

        onView(allOf(isAssignableFrom(RecyclerView::class.java), withId(R.id.rvMatchListSearch)))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(withId(R.id.rvMatchListSearch))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(isDisplayed()))
            .check(matches(hasDescendant(withText("Arsenal"))))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun typeTextOnSearchView_dataNotAvailable_shouldReturnNoData() {

        onView(withId(R.id.searchViewEdit))
            .check(matches(isDisplayed()))
            .check(matches(isFocusable()))

        onView(isAssignableFrom(EditText::class.java))
            .perform(typeText("Wkwkwk"), pressImeActionButton())

        onView(allOf(isAssignableFrom(RecyclerView::class.java), withId(R.id.rvMatchListSearch)))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))

        onView(withId(R.id.layoutNoDataSearch))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @After
    fun complete(){
        idlingRegistry.unregister(TestIdlingResource.idlingResource)
    }
}