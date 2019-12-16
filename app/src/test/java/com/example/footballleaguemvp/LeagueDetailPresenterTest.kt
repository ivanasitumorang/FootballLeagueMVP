package com.example.footballleaguemvp

import com.example.footballleaguemvp.network.NetworkService
import com.example.footballleaguemvp.network.TestSchedulerProvider
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailContract
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailPresenter
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.schedulers.TestScheduler
import org.junit.Before


/**
 * Created by ivanaazuka on 2019-12-16.
 * Android Engineer
 */
 
class LeagueDetailPresenterTest {
    private lateinit var leagueDetailPresenter: LeagueDetailPresenter
    private val leagueDetailView: LeagueDetailContract.View = mock()
    private val networkService: NetworkService = mock()

    @Before
    fun setup(){
        val testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
    }
}