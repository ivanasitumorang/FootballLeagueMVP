package com.example.footballleaguemvp.network

import com.example.footballleaguemvp.data.LeagueResponse
import com.example.footballleaguemvp.data.MatchResponse
import com.example.footballleaguemvp.data.SearchMatchResponse
import com.example.footballleaguemvp.data.TeamResponse
import io.reactivex.Flowable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by ivanaazuka on 2019-11-26.
 * Android Engineer
 */

private const val BASE_URL = "https://www.thesportsdb.com/"

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface NetworkService {
    @GET("api/v1/json/1/lookupleague.php")
    fun getLeagueDetail(
        @Query("id") idLeague: String): Flowable<LeagueResponse>

    @GET("api/v1/json/1/eventspastleague.php")
    fun getPrevMatchByLeagueId(
        @Query("id") idLeague: String): Flowable<MatchResponse>

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatchByLeagueId(
        @Query("id") idLeague: String): Flowable<MatchResponse>

    @GET("api/v1/json/1/lookupevent.php")
    fun getMatchDetail(
        @Query("id") idEvent: String) : Flowable<MatchResponse>

    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(
        @Query("id") idTeam: String) : Flowable<TeamResponse>

    @GET("api/v1/json/1/searchevents.php")
    fun searchMatchByQuery(
        @Query("e") query: String) : Flowable<SearchMatchResponse>
}

object NetworkServiceApi {
    val retrofitService: NetworkService by lazy {
        retrofit.create(NetworkService::class.java)
    }
}