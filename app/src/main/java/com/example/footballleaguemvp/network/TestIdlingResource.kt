package com.example.footballleaguemvp.network

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource


/**
 * Created by ivanaazuka on 2019-12-18.
 * Android Engineer
 */
class TestIdlingResource {
    companion object {
        private val _countingIdleResource = CountingIdlingResource("TEST")
        val idlingResource: IdlingResource
            get() = _countingIdleResource

        fun increment(){
            _countingIdleResource.increment()
        }

        fun decrement(){
            _countingIdleResource.decrement()
        }
    }
}