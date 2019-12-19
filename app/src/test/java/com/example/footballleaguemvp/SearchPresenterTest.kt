package com.example.footballleaguemvp

import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.data.SearchMatchResponse
import com.example.footballleaguemvp.network.NetworkService
import com.example.footballleaguemvp.network.TestRetrofitServiceProvider
import com.example.footballleaguemvp.network.TestSchedulerProvider
import com.example.footballleaguemvp.ui.searchmatch.SearchContract
import com.example.footballleaguemvp.ui.searchmatch.SearchPresenter
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
class SearchPresenterTest {
    private lateinit var searchPresenter: SearchPresenter
    private lateinit var testScheduler: TestScheduler
    private lateinit var testServiceProvider: TestRetrofitServiceProvider
    private val searchView: SearchContract.View = mockk(relaxed = true)
    private val networkService: NetworkService = mock()

    @Before
    fun setup() {
        testScheduler = TestScheduler()
        val testSchedulerProvider = TestSchedulerProvider(testScheduler)
        testServiceProvider = TestRetrofitServiceProvider(networkService)
        searchPresenter = SearchPresenter(searchView, testSchedulerProvider, testServiceProvider)
    }

    @Test
    fun `get search data based on query and filtering soccer only should be success`() {
        val query = "liverpool"
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
        val matchResponseMock = SearchMatchResponse(event = listOf(matchDetailMock))

        val filteredSoccerResult = matchResponseMock.event.filter { it.strSport.equals("soccer", true) }

        doReturn(Flowable.just(matchResponseMock))
            .`when`(testServiceProvider.getNetworkService())
            .searchMatchByQuery(query)

        searchPresenter.getSearchedData(query)

        testScheduler.triggerActions()

        verifyOrder {
            with(searchView) {
                showLoadingIndicator()
                populateData(filteredSoccerResult)
                hideLoadingIndicator()
            }
        }
    }

    @Test
    fun `get search data based on query and filtering soccer only should be failed`() {
        val query = "liverpool"
        val errorMessageMock = Throwable("error")
        val errorResponseMock = Flowable.error<Throwable>(errorMessageMock)

        doReturn(errorResponseMock)
            .`when`(testServiceProvider.getNetworkService())
            .searchMatchByQuery(query)

        searchPresenter.getSearchedData(query)

        testScheduler.triggerActions()

        verifyOrder {
            with(searchView) {
                showLoadingIndicator()
                hideLoadingIndicator()
                showNoData()
            }
        }
    }
}