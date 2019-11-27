package com.example.footballleaguemvp.ui.leaguedetail

import com.example.footballleaguemvp.network.NetworkServiceApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */

class LeagueDetailPresenter constructor(private val view: LeagueDetailContract.View) :
    LeagueDetailContract.Logic {

    private lateinit var mDisposable: Disposable

    override fun setLeagueDetail(idLeague: String) {
        view.showLoadingIndicator()
        view.disableButtonSeeMatch()
        mDisposable = NetworkServiceApi.retrofitService
            .getLeagueDetail(idLeague)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    view.populateData(it.leagues[0])
                    view.hideLoadingIndicator()
                    view.enableButtonSeeMatch()
                },
                {
                    view.showLoadingIndicator()
                    view.disableButtonSeeMatch()
                }
            )
    }

}