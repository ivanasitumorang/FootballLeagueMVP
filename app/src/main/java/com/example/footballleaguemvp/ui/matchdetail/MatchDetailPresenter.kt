package com.example.footballleaguemvp.ui.matchdetail

import com.example.footballleaguemvp.network.AppSchedulerProvider
import com.example.footballleaguemvp.network.NetworkServiceApi
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by ivanaazuka on 2019-11-28.
 * Android Engineer
 */
 
class MatchDetailPresenter constructor(private val view: MatchDetailContract.View) : MatchDetailContract.Logic {

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    private val scheduler = AppSchedulerProvider()

    override fun getMatchDetail(matchId: String) {
        mCompositeDisposable.add(
            NetworkServiceApi.retrofitService
                .getMatchDetail(matchId)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe(
                    {
                        val result = it.events[0]
                        view.displayMatchDetail(result, result.idHomeTeam, result.idAwayTeam)
                    },
                    {
                    }
                )
        )
    }

    override fun getTeamDetail(teamHomeId: String, teamAwayId: String) {
        mCompositeDisposable.addAll(
            NetworkServiceApi.retrofitService
                .getTeamDetail(teamHomeId)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe(
                    {
                        view.displayHomeTeamDetail(it.teams[0])
                    },
                    {
                    }
                ),
            NetworkServiceApi.retrofitService
                .getTeamDetail(teamAwayId)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribe(
                    {
                        view.displayAwayTeamDetail(it.teams[0])
                    },
                    {
                    }
                )
        )
    }

}