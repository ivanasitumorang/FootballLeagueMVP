package com.example.footballleaguemvp.ui.leaguelist

import com.example.footballleaguemvp.data.FootballLeagueData


/**
 * Created by ivanaazuka on 2019-11-26.
 * Android Engineer
 */
 
class LeagueListPresenter constructor(private val view: LeagueListContract.View) : LeagueListContract.Logic {

    override fun setData() {
        val leagues = FootballLeagueData.data
        view.populateData(leagues)
    }

}