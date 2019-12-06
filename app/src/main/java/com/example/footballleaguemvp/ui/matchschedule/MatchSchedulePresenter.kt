package com.example.footballleaguemvp.ui.matchschedule

import com.example.footballleaguemvp.network.NetworkServiceApi
import com.example.footballleaguemvp.ui.matchlist.MatchListPagerAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class MatchSchedulePresenter constructor(private val view: MatchScheduleContract.View) : MatchScheduleContract.Logic {

    private lateinit var mDisposable: Disposable

    override fun getMatchList(type: String, leagueId: String) {
        if (type.equals(MatchListPagerAdapter.TAG_TYPE_PREV_MATCH, true)) {
            mDisposable = NetworkServiceApi.retrofitService
                .getPrevMatchByLeagueId(leagueId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        view.populateData(it.events)
                    },
                    {
                    })
        } else {
            mDisposable = NetworkServiceApi.retrofitService
                .getNextMatchByLeagueId(leagueId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        view.populateData(it.events)
                    },
                    {
                    })
        }

    }

}