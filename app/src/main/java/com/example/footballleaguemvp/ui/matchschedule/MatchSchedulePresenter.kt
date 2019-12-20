package com.example.footballleaguemvp.ui.matchschedule

import com.example.footballleaguemvp.network.NetworkServiceProvider
import com.example.footballleaguemvp.network.SchedulerProvider
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailPagerAdapter
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class MatchSchedulePresenter constructor(private val view: MatchScheduleContract.View, private val schedulerProvider: SchedulerProvider, private val networkServiceProvider: NetworkServiceProvider) : MatchScheduleContract.Logic {

    private lateinit var mDisposable: Disposable

    override fun getMatchList(type: String, leagueId: String) {
        if (type.equals(LeagueDetailPagerAdapter.TAG_TYPE_PREV_MATCH, true)) {
            view.showLoadingIndicator()
            mDisposable = networkServiceProvider.getNetworkService()
                .getPrevMatchByLeagueId(leagueId)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        view.populateData(it.events)
                        view.hideLoadingIndicator()
                    },
                    {
                        view.hideLoadingIndicator()
                        view.showNoData()
                    })
        } else {
            view.showLoadingIndicator()
            mDisposable = networkServiceProvider.getNetworkService()
                .getNextMatchByLeagueId(leagueId)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        view.populateData(it.events)
                        view.hideLoadingIndicator()
                    },
                    {
                        view.hideLoadingIndicator()
                        view.showNoData()
                    })
        }

    }

}