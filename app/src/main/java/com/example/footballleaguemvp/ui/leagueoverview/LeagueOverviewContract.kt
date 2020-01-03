package com.example.footballleaguemvp.ui.leagueoverview

import com.example.footballleaguemvp.data.League


/**
 * Created by ivanaazuka on 2019-12-20.
 * Android Engineer
 */
 
class LeagueOverviewContract {
    interface View {
        fun setUi()
        fun setPresenter()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun populateLeagueDetail(league: League)
        fun initializeData()
    }

    interface Logic {
        fun fetchLeagueDetail(idLeague: String)
    }
}