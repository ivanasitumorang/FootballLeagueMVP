package com.example.footballleaguemvp

import com.example.footballleaguemvp.data.League
import com.example.footballleaguemvp.network.NetworkService
import com.example.footballleaguemvp.network.TestSchedulerProvider
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailContract
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailPresenter
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test


/**
 * Created by ivanaazuka on 2019-12-16.
 * Android Engineer
 */
 
class LeagueDetailPresenterTest {
    private lateinit var leagueDetailPresenter: LeagueDetailPresenter
    private lateinit var testScheduler: TestScheduler
    private val leagueDetailView: LeagueDetailContract.View = mock()
    private val networkService: NetworkService = mock()

    @Before
    fun setup(){
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        leagueDetailPresenter = LeagueDetailPresenter(leagueDetailView, testSchedulerProvider)
    }

    @Test
    fun `set the league detail information based on id league should be success`(){
        val leagueId = "4328"
        val leagueDetailMock = League(
            idLeague = leagueId,
            strBadge = "https://www.thesportsdb.com/images/media/league/badge/i6o0kh1549879062.png",
            strBanner = "https://www.thesportsdb.com/images/media/league/banner/4m3g4s1520767740.jpg",
            strDescriptionEN = "The Premier League (often referred to as the English Premier League (EPL) outside England), is the top level of the English football league system. Contested by 20 clubs, it operates on a system of promotion and relegation with the English Football League (EFL).\r\n\r\nThe Premier League is a corporation in which the member clubs act as shareholders. Seasons run from August to May with each team playing 38 matches (playing each other home and away). Most games are played on Saturday and Sunday afternoons. The Premier League has featured 47 English and two Welsh clubs since its inception, making it a cross-border league.\r\n\r\nThe competition was formed as the FA Premier League on 20 February 1992 following the decision of clubs in the Football League First Division to break away from the Football League, founded in 1888, and take advantage of a lucrative television rights deal. The deal was worth £1 billion a year domestically as of 2013–14, with BSkyB and BT Group securing the domestic rights to broadcast 116 and 38 games respectively. The league generates €2.2 billion per year in domestic and international television rights. In 2014–15, teams were apportioned revenues of £1.6 billion, rising sharply to £2.4 billion in 2016–17.\r\n\r\nThe Premier League is the most-watched sports league in the world, broadcast in 212 territories to 643 million homes and a potential TV audience of 4.7 billion people. In the 2014–15 season, the average Premier League match attendance exceeded 36,000, second highest of any professional football league behind the Bundesliga's 43,500. Most stadium occupancies are near capacity. The Premier League ranks second in the UEFA coefficients of leagues based on performances in European competitions over the past five seasons, as of 2018.\r\n\r\nForty-nine clubs have competed since the inception of the Premier League in 1992. Six of them have won the title: Manchester United (13), Chelsea (5), Arsenal (3), Manchester City (3), Blackburn Rovers (1), and Leicester City (1). Following the 2003–04 season, Arsenal acquired the nickname \"The Invincibles\" as they became, and still remain, the only club to complete a Premier League campaign without losing a single game. The record of most points in a season is 100 by Manchester City in 2017–18.",
            strLeague = "English Premier League",
            strLogo = "https://www.thesportsdb.com/images/media/league/logo/4c377s1535214890.png"
        )
        val leagueResponseMock = listOf(leagueDetailMock)

        doReturn(Flowable.just(leagueResponseMock))
            .`when`(networkService)
            .getLeagueDetail(leagueId)

        leagueDetailPresenter.setLeagueDetail(leagueId)
        testScheduler.triggerActions()

        verify(leagueDetailView).showLoadingIndicator()
        verify(leagueDetailView).disableButtonSeeMatch()
        verify(leagueDetailView).populateData(leagueResponseMock[0])
        verify(leagueDetailView).hideLoadingIndicator()
        verify(leagueDetailView).enableButtonSeeMatch()
    }

    @Test
    fun `set the league detail information based on id league should be failed`(){
        val leagueId = "0000"

        val errorMessageMock = Throwable("error")
        val errorResponseMock = Flowable.error<Throwable>(errorMessageMock)

        doReturn(errorResponseMock)
            .`when`(networkService)
            .getLeagueDetail(leagueId)

        leagueDetailPresenter.setLeagueDetail(leagueId)
        testScheduler.triggerActions()

        verify(leagueDetailView).showLoadingIndicator()
        verify(leagueDetailView).disableButtonSeeMatch()
        verify(leagueDetailView).hideLoadingIndicator()
    }
}