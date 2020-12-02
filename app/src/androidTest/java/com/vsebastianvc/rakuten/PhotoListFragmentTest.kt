package com.vsebastianvc.rakuten

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vsebastianvc.rakuten.itemlist.activities.ItemListActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Sebastian on 12/02/2020.
 */
@RunWith(AndroidJUnit4::class)
class PhotoListFragmentTest {

    @Before
    fun setup() {
        ActivityScenario.launch(ItemListActivity::class.java)
    }

    @Test
    fun swipeUpListViewTest() {
        onView(withId(R.id.lv_photos)).perform(ViewActions.swipeUp())
    }

    @After
    fun tearDown() {
        //Do something after each test finish
        //Nothing to do here at least for now
    }
}