package com.example.footballleaguemvp.ui.leaguedetail

import com.example.footballleaguemvp.network.NetworkServiceApi
import com.example.footballleaguemvp.network.SchedulerProvider
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */

class LeagueDetailPresenter constructor(private val view: LeagueDetailContract.View, private val schedulerProvider: SchedulerProvider) :
    LeagueDetailContract.Logic {

    private lateinit var mDisposable: Disposable

    override fun setLeagueDetail(idLeague: String) {
        view.showLoadingIndicator()
        view.disableButtonSeeMatch()
        mDisposable = NetworkServiceApi.retrofitService
            .getLeagueDetail(idLeague)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe(
                {
                    view.populateData(it.leagues[0])
                    view.hideLoadingIndicator()
                    view.enableButtonSeeMatch()
                },
                {
                    view.hideLoadingIndicator()
                    view.disableButtonSeeMatch()
                }
            )
    }

}