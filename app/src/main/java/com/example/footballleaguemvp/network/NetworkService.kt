package com.example.footballleaguemvp.network

import com.example.footballleaguemvp.data.LeagueResponse
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
        @Query("id") idLeague: String)

    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatchByLeagueId(
        @Query("id") idLeague: String)

    @GET("api/v1/json/1/lookupevent.php")
    fun getMatchDetail(
        @Query("id") idEvent: String)
}

object NetworkServiceApi {
    val retrofitService: NetworkService by lazy {
        retrofit.create(NetworkService::class.java)
    }
}