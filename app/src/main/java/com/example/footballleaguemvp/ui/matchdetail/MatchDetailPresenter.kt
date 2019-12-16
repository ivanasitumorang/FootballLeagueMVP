package com.example.footballleaguemvp.ui.matchdetail

import com.example.footballleaguemvp.network.*
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by ivanaazuka on 2019-11-28.
 * Android Engineer
 */
 
class MatchDetailPresenter constructor(private val view: MatchDetailContract.View, private val schedulerProvider: SchedulerProvider, private val networkServiceProvider: NetworkServiceProvider) : MatchDetailContract.Logic {

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getMatchDetail(matchId: String) {
        mCompositeDisposable.add(
            networkServiceProvider.getNetworkService()
                .getMatchDetail(matchId)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
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
            networkServiceProvider.getNetworkService()
                .getTeamDetail(teamHomeId)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        view.displayHomeTeamDetail(it.teams[0])
                    },
                    {
                    }
                ),
            networkServiceProvider.getNetworkService()
                .getTeamDetail(teamAwayId)
                .observeOn(schedulerProvider.ui())
                .subscribeOn(schedulerProvider.io())
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