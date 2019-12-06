package com.example.footballleaguemvp.ui.matchdetail

import com.example.footballleaguemvp.network.NetworkServiceApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by ivanaazuka on 2019-11-28.
 * Android Engineer
 */
 
class MatchDetailPresenter constructor(private val view: MatchDetailContract.View) : MatchDetailContract.Logic {

    private var mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getMatchDetail(matchId: String) {
        mCompositeDisposable.add(
            NetworkServiceApi.retrofitService
                .getMatchDetail(matchId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        view.displayHomeTeamDetail(it.teams[0])
                    },
                    {
                    }
                ),
            NetworkServiceApi.retrofitService
                .getTeamDetail(teamAwayId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
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