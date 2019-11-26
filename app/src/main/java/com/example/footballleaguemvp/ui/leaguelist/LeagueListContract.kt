package com.example.footballleaguemvp.ui.leaguelist

import com.example.footballleaguemvp.data.LocalLeague


/**
 * Created by ivanaazuka on 2019-11-26.
 * Android Engineer
 */
 
class LeagueListContract {
    interface View {
        fun setPresenter()
        fun setupUi()
        fun setupToolbar()
        fun setupNavigation()
        fun initializeData()
        fun populateData(leagueList: List<LocalLeague>)
    }

    interface Logic {
        fun setData()
    }
}