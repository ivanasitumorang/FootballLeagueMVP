package com.example.footballleaguemvp

import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.data.MatchResponse
import com.example.footballleaguemvp.data.Team
import com.example.footballleaguemvp.data.TeamResponse
import com.example.footballleaguemvp.network.NetworkService
import com.example.footballleaguemvp.network.TestRetrofitServiceProvider
import com.example.footballleaguemvp.network.TestSchedulerProvider
import com.example.footballleaguemvp.ui.matchdetail.MatchDetailContract
import com.example.footballleaguemvp.ui.matchdetail.MatchDetailPresenter
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
 
class MatchDetailPresenterTest {

    private lateinit var matchDetailPresenter: MatchDetailPresenter
    private lateinit var testScheduler: TestScheduler
    private lateinit var testServiceProvider: TestRetrofitServiceProvider
    private val matchDetailView: MatchDetailContract.View = mockk(relaxed = true)
    private val networkService: NetworkService = mock()

    @Before
    fun setup(){
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        testServiceProvider = TestRetrofitServiceProvider(networkService)
        matchDetailPresenter = MatchDetailPresenter(matchDetailView, testSchedulerProvider, testServiceProvider)
    }

    @Test
    fun `set the match detail information based on match id should be success`(){
        val matchId = "441"
        val idAwayTeam = "133614"
        val idHomeTeam = "133602"
        val matchDetailMock = Match(
            idEvent = matchId,
            dateEvent = "2014-12-29",
            idAwayTeam = idAwayTeam,
            idHomeTeam = idHomeTeam,
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
            .getMatchDetail(matchId)

        matchDetailPresenter.getMatchDetail(matchId)
        testScheduler.triggerActions()

        verifyOrder {
            matchDetailView.displayMatchDetail(matchResponseMock.events[0], idHomeTeam, idAwayTeam)
        }
    }

    @Test
    fun `set the match detail information based on match id should be failed`(){
        val matchId = "441"

        val errorMessageMock = Throwable("error")
        val errorResponseMock = Flowable.error<Throwable>(errorMessageMock)

        doReturn(errorResponseMock)
            .`when`(testServiceProvider.getNetworkService())
            .getMatchDetail(matchId)

        matchDetailPresenter.getMatchDetail(matchId)
        testScheduler.triggerActions()
    }

    @Test
    fun `get the team detail information based on team home id and team away id should be success`(){
        val idAwayTeam = "133614"
        val idHomeTeam = "133602"
        val teamHomeDetailMock = Team(
            strTeam = "home mock url",
            strTeamLogo = "home mock url juga"

        )
        val teamAwayDetailMock = Team(
            strTeam = "away mock url",
            strTeamLogo = "away mock url juga"

        )
        val teamHomeResponseMock = TeamResponse(teams = listOf(teamHomeDetailMock))
        val teamAwayResponseMock = TeamResponse(teams = listOf(teamAwayDetailMock))

        doReturn(Flowable.just(teamHomeResponseMock))
            .`when`(testServiceProvider.getNetworkService())
            .getTeamDetail(idHomeTeam)

        doReturn(Flowable.just(teamAwayResponseMock))
            .`when`(testServiceProvider.getNetworkService())
            .getTeamDetail(idAwayTeam)

        matchDetailPresenter.getTeamDetail(idHomeTeam, idAwayTeam)
        testScheduler.triggerActions()

        verifyOrder {
            with(matchDetailView) {
                displayHomeTeamDetail(teamHomeResponseMock.teams[0])
                displayAwayTeamDetail(teamAwayResponseMock.teams[0])
            }
        }
    }

    @Test
    fun `get the team detail information based on team home id and team away id should be failed`(){
        val idAwayTeam = "133614"
        val idHomeTeam = "133602"
        val errorMessageMock = Throwable("error")
        val errorResponseMock = Flowable.error<Throwable>(errorMessageMock)

        doReturn(errorResponseMock)
            .`when`(testServiceProvider.getNetworkService())
            .getTeamDetail(idHomeTeam)

        doReturn(errorResponseMock)
            .`when`(testServiceProvider.getNetworkService())
            .getTeamDetail(idAwayTeam)

        matchDetailPresenter.getTeamDetail(idHomeTeam, idAwayTeam)
        testScheduler.triggerActions()
    }
}