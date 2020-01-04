package com.example.footballleaguemvp.ui.teamdetail

import com.example.footballleaguemvp.network.NetworkServiceProvider
import com.example.footballleaguemvp.network.SchedulerProvider
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2020-01-04.
 * Android Engineer
 */
 
class TeamDetailPresenter constructor(private val view: TeamDetailContract.View, private val schedulerProvider: SchedulerProvider, private val networkServiceProvider: NetworkServiceProvider)
    : TeamDetailContract.Logic {

    lateinit var disposable: Disposable

    override fun getTeamDetail(teamId: String) {
        view.showLoadingIndicator()
        disposable = networkServiceProvider.getNetworkService()
            .getTeamDetail(teamId)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(
                {
                    view.displayTeamDetail(it.teams[0])
                    view.hideLoadingIndicator()
                },
                {
                    view.hideLoadingIndicator()
                }
            )
    }

}