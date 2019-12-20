package com.example.footballleaguemvp.ui.leaguedetail


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class LeagueDetailContract {
    interface View {
        fun setUi()
        fun setupToolbar(title: String)
        fun setupNavigation()
        fun setupClickListener()
        fun loadSelectedTab()
    }

    interface Logic {
//        fun setLeagueDetail(idLeague: String)
    }
}