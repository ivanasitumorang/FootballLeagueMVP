package com.example.footballleaguemvp.ui.teamdetail

import com.example.footballleaguemvp.data.Team


/**
 * Created by ivanaazuka on 2020-01-04.
 * Android Engineer
 */
 
class TeamDetailContract {
    interface View {
        fun setupUi()
        fun setupPresenter()
        fun setupToolbar(title: String)
        fun initializeData()
        fun displayTeamDetail(team: Team)
        fun setupClickListener()
        fun setupNavigation()
        fun addTeamToFavorite(team: Team)
        fun removeTeamFromFavorite(team: Team)
        fun toggleFavoriteIcon()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
    }

    interface Logic {
        fun getTeamDetail(teamId: String)
    }
}