package com.example.footballleaguemvp.ui.leagueoverview

import com.example.footballleaguemvp.network.NetworkServiceProvider
import com.example.footballleaguemvp.network.SchedulerProvider
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2019-12-20.
 * Android Engineer
 */
 
class LeagueOverviewPresenter constructor(private val view: LeagueOverviewContract.View, private val schedulerProvider: SchedulerProvider, private val networkServiceProvider: NetworkServiceProvider)
    : LeagueOverviewContract.Logic {

    lateinit var disposable: Disposable

    override fun fetchLeagueDetail(idLeague: String) {
        view.showLoadingIndicator()
        disposable = networkServiceProvider.getNetworkService()
            .getLeagueDetail(idLeague)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe(
                {
                    view.populateLeagueDetail(it.leagues[0])
                    view.hideLoadingIndicator()
                },
                {
                    view.hideLoadingIndicator()
                }
            )
    }

}