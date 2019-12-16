package com.example.footballleaguemvp.ui.matchschedule

import com.example.footballleaguemvp.network.NetworkServiceApi
import com.example.footballleaguemvp.network.SchedulerProvider
import com.example.footballleaguemvp.ui.matchlist.MatchListPagerAdapter
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class MatchSchedulePresenter constructor(private val view: MatchScheduleContract.View, private val schedulerProvider: SchedulerProvider) : MatchScheduleContract.Logic {

    private lateinit var mDisposable: Disposable

    override fun getMatchList(type: String, leagueId: String) {
        if (type.equals(MatchListPagerAdapter.TAG_TYPE_PREV_MATCH, true)) {
            view.showLoadingIndicator()
            mDisposable = NetworkServiceApi.retrofitService
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
            mDisposable = NetworkServiceApi.retrofitService
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