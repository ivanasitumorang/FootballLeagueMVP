package com.example.footballleaguemvp.ui.leagueclassement

import com.example.footballleaguemvp.network.NetworkServiceProvider
import com.example.footballleaguemvp.network.SchedulerProvider
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2020-01-06.
 * Android Engineer
 */
 
class LeagueClassementPresenter constructor(private val view: LeagueClassementContract.View, private val schedulerProvider: SchedulerProvider, private val networkServiceProvider: NetworkServiceProvider) : LeagueClassementContract.Logic {

    lateinit var mDisposable: Disposable

    override fun getClassements(leagueId: String) {
        view.showLoadingIndicator()
        mDisposable = networkServiceProvider.getNetworkService()
            .getClassementList(leagueId)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
            .subscribe(
                {
                    view.populateData(it.table)
                    view.hideLoadingIndicator()
                },
                {
                    view.hideLoadingIndicator()
                    view.showNoData()
                })
    }

}