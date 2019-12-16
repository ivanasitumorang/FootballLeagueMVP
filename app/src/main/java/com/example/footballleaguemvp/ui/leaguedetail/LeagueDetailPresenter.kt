package com.example.footballleaguemvp.ui.leaguedetail

import com.example.footballleaguemvp.network.AppSchedulerProvider
import com.example.footballleaguemvp.network.NetworkServiceApi
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */

class LeagueDetailPresenter constructor(private val view: LeagueDetailContract.View) :
    LeagueDetailContract.Logic {

    private lateinit var mDisposable: Disposable
    private val scheduler = AppSchedulerProvider()

    override fun setLeagueDetail(idLeague: String) {
        view.showLoadingIndicator()
        view.disableButtonSeeMatch()
        mDisposable = NetworkServiceApi.retrofitService
            .getLeagueDetail(idLeague)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribe(
                {
                    view.populateData(it.leagues[0])
                    view.hideLoadingIndicator()
                    view.enableButtonSeeMatch()
                },
                {
                    view.showLoadingIndicator()
                    view.disableButtonSeeMatch()
                }
            )
    }

}