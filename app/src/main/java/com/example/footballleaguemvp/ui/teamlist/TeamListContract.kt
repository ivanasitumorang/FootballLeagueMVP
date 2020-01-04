package com.example.footballleaguemvp.ui.teamlist

import com.example.footballleaguemvp.data.Team


/**
 * Created by ivanaazuka on 2020-01-03.
 * Android Engineer
 */
 
class TeamListContract {
    interface View {
        fun setupUi()
        fun setupPresenter()
        fun setupNavigation()
        fun initializeData()
        fun populateData(teams: List<Team>)
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showNoData()
    }

    interface Logic {
        fun getTeamList(leagueId: String)
    }
}