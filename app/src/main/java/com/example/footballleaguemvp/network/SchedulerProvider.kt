package com.example.footballleaguemvp.network

import io.reactivex.Scheduler


/**
 * Created by ivanaazuka on 2019-12-16.
 * Android Engineer
 */

interface SchedulerProvider {
    fun ui() : Scheduler
    fun computation() : Scheduler
    fun io() : Scheduler
}