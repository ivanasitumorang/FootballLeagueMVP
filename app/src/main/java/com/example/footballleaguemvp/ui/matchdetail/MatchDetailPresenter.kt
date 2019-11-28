package com.example.footballleaguemvp.ui.matchdetail

import com.example.footballleaguemvp.network.NetworkServiceApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by ivanaazuka on 2019-11-28.
 * Android Engineer
 */
 
class MatchDetailPresenter constructor(private val view: MatchDetailContract.View) : MatchDetailContract.Logic {

    private lateinit var mDisposable: Disposable

    override fun getMatchDetail(matchId: String) {
        view.showLoadingIndicator()
        mDisposable = NetworkServiceApi.retrofitService
            .getMatchDetail(matchId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    view.displayData(it.events[0])
                    view.hideLoadingIndicator()
                },
                {
                    view.hideLoadingIndicator()
                }
            )
    }

}