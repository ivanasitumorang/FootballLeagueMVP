package com.example.footballleaguemvp

import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.data.MatchResponse
import com.example.footballleaguemvp.network.NetworkService
import com.example.footballleaguemvp.network.TestRetrofitServiceProvider
import com.example.footballleaguemvp.network.TestSchedulerProvider
import com.example.footballleaguemvp.ui.matchschedule.MatchScheduleContract
import com.example.footballleaguemvp.ui.matchschedule.MatchSchedulePresenter
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.mockk.mockk
import io.mockk.verifyOrder
import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test


/**
 * Created by ivanaazuka on 2019-12-17.
 * Android Engineer
 */
 
class MatchSchedulePresenterTest {
    private lateinit var matchSchedulePresenter: MatchSchedulePresenter
    private lateinit var testScheduler: TestScheduler
    private lateinit var testServiceProvider: TestRetrofitServiceProvider
    private val matchScheduleView: MatchScheduleContract.View = mockk(relaxed = true)
    private val networkService: NetworkService = mock()

    @Before
    fun setup(){
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        testServiceProvider = TestRetrofitServiceProvider(networkService)
        matchSchedulePresenter = MatchSchedulePresenter(matchScheduleView, testSchedulerProvider, testServiceProvider)
    }

    @Test
    fun `get previous match list based on league id should be success`() {
        val leagueId = "4328"
        val matchDetailMock = Match(
            idEvent = "1234",
            dateEvent = "2014-12-29",
            idAwayTeam = "",
            idHomeTeam = "",
            strTime = "20:00:00+00:00",
            intAwayScore = "1",
            intHomeScore = "0",
            strAwayGoalDetails = "52': Gylfi Sigurdsson;",
            strAwayRedCards = "",
            strAwayTeam = "",
            strAwayYellowCards = "",
            strEvent = "Liverpool vs Swansea",
            strHomeGoalDetails = "",
            strHomeRedCards = "",
            strHomeTeam = "",
            strHomeYellowCards = "",
            strSport = "soccer"
        )
        val matchResponseMock = MatchResponse(events = listOf(matchDetailMock))

        doReturn(Flowable.just(matchResponseMock))
            .`when`(testServiceProvider.getNetworkService())
            .getPrevMatchByLeagueId(leagueId)

        matchSchedulePresenter.getMatchList("previous_match", leagueId)
        testScheduler.triggerActions()

        verifyOrder {
            with(matchScheduleView){
                showLoadingIndicator()
                populateData(matchResponseMock.events)
                hideLoadingIndicator()
            }
        }
    }

    @Test
    fun `get previous match list based on league id should be failed`() {
        val leagueId = "4328"
        val errorMessageMock = Throwable("error")
        val errorResponseMock = Flowable.error<Throwable>(errorMessageMock)

        doReturn(Flowable.just(errorResponseMock))
            .`when`(testServiceProvider.getNetworkService())
            .getPrevMatchByLeagueId(leagueId)

        matchSchedulePresenter.getMatchList("previous_match", leagueId)
        testScheduler.triggerActions()

        verifyOrder {
            with(matchScheduleView){
                showLoadingIndicator()
                hideLoadingIndicator()
                showNoData()
            }
        }
    }

    @Test
    fun `get next match list based on league id should be success`() {
        val leagueId = "4328"
        val matchDetailMock = Match(
            idEvent = "1234",
            dateEvent = "2014-12-29",
            idAwayTeam = "",
            idHomeTeam = "",
            strTime = "20:00:00+00:00",
            intAwayScore = "1",
            intHomeScore = "0",
            strAwayGoalDetails = "52': Gylfi Sigurdsson;",
            strAwayRedCards = "",
            strAwayTeam = "",
            strAwayYellowCards = "",
            strEvent = "Liverpool vs Swansea",
            strHomeGoalDetails = "",
            strHomeRedCards = "",
            strHomeTeam = "",
            strHomeYellowCards = "",
            strSport = "soccer"
        )
        val matchResponseMock = MatchResponse(events = listOf(matchDetailMock))

        doReturn(Flowable.just(matchResponseMock))
            .`when`(testServiceProvider.getNetworkService())
            .getNextMatchByLeagueId(leagueId)

        matchSchedulePresenter.getMatchList("next_match", leagueId)
        testScheduler.triggerActions()

        verifyOrder {
            with(matchScheduleView){
                showLoadingIndicator()
                populateData(matchResponseMock.events)
                hideLoadingIndicator()
            }
        }
    }

    @Test
    fun `get next match list based on league id should be failed`() {
        val leagueId = "4328"
        val errorMessageMock = Throwable("error")
        val errorResponseMock = Flowable.error<Throwable>(errorMessageMock)

        doReturn(errorResponseMock)
            .`when`(testServiceProvider.getNetworkService())
            .getNextMatchByLeagueId(leagueId)

        matchSchedulePresenter.getMatchList("next_match", leagueId)
        testScheduler.triggerActions()

        verifyOrder {
            with(matchScheduleView){
                showLoadingIndicator()
                hideLoadingIndicator()
                showNoData()
            }
        }
    }
}
