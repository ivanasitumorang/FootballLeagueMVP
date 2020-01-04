package com.example.footballleaguemvp.ui.teamlist

import com.example.footballleaguemvp.network.NetworkServiceProvider
import com.example.footballleaguemvp.network.SchedulerProvider
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2020-01-03.
 * Android Engineer
 */
 

class TeamListPresenter constructor(private val view: TeamListContract.View, private val schedulerProvider: SchedulerProvider, private val networkServiceProvider: NetworkServiceProvider) : TeamListContract.Logic {

    lateinit var mDisposable: Disposable

    override fun getTeamList(leagueId: String) {
        view.showLoadingIndicator()
        mDisposable = networkServiceProvider.getNetworkService()
            .getTeamList(leagueId)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe(
                {
                    view.populateData(it.teams)
                    view.hideLoadingIndicator()
                },
                {
                    view.hideLoadingIndicator()
                    view.showNoData()
                })
    }

}