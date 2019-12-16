package com.example.footballleaguemvp.network

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by ivanaazuka on 2019-12-16.
 * Android Engineer
 */

class AppSchedulerProvider: SchedulerProvider {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun computation(): Scheduler = Schedulers.computation()
    override fun io(): Scheduler = Schedulers.io()

}