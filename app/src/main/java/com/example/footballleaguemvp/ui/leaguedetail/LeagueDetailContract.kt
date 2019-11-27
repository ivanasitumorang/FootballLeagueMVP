package com.example.footballleaguemvp.ui.leaguedetail

import com.example.footballleaguemvp.data.League


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class LeagueDetailContract {
    interface View {
        fun setPresenter()
        fun setUi()
        fun setupToolbar(title: String)
        fun setupNavigation()
        fun initializeData()
        fun populateData(league: League)
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun setupClickListener()
        fun enableButtonSeeMatch()
        fun disableButtonSeeMatch()
    }

    interface Logic {
        fun setLeagueDetail(idLeague: String)
    }
}