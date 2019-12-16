package com.example.footballleaguemvp.ui.searchmatch

import com.example.footballleaguemvp.network.AppSchedulerProvider
import com.example.footballleaguemvp.network.NetworkServiceApi
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class SearchPresenter constructor(private val view: SearchContract.View) : SearchContract.Logic {

    private lateinit var mDisposable: Disposable
    private val scheduler = AppSchedulerProvider()

    override fun getSearchedData(query: String) {
        view.showLoadingIndicator()
        mDisposable = NetworkServiceApi.retrofitService
            .searchMatchByQuery(query)
            .observeOn(scheduler.ui())
            .subscribeOn(scheduler.io())
            .subscribe(
                { response ->
                    val resultOnlySoccer = response.event.filter { match ->
                        match.strSport.equals("Soccer", true)
                    }
                    view.populateData(resultOnlySoccer)
                    view.hideLoadingIndicator()
                },
                {
                    view.hideLoadingIndicator()
                    view.showNoData()
                }
            )
    }

}