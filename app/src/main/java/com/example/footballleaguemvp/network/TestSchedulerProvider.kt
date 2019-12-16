package com.example.footballleaguemvp.network

import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler


/**
 * Created by ivanaazuka on 2019-12-16.
 * Android Engineer
 */

class TestSchedulerProvider constructor(private val testScheduler: TestScheduler) :
    SchedulerProvider {
    override fun ui(): Scheduler = testScheduler
    override fun computation(): Scheduler = testScheduler
    override fun io(): Scheduler = testScheduler
}