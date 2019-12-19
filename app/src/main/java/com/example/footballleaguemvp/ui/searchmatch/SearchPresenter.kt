package com.example.footballleaguemvp.ui.searchmatch

import com.example.footballleaguemvp.network.NetworkServiceProvider
import com.example.footballleaguemvp.network.SchedulerProvider
import io.reactivex.disposables.Disposable


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class SearchPresenter constructor(private val view: SearchContract.View, private val schedulerProvider: SchedulerProvider, private val networkServiceProvider: NetworkServiceProvider) : SearchContract.Logic {

    private lateinit var mDisposable: Disposable

    override fun getSearchedData(query: String) {

        view.showLoadingIndicator()
        mDisposable = networkServiceProvider.getNetworkService()
            .searchMatchByQuery(query)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
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