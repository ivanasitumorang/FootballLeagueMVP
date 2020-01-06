package com.example.footballleaguemvp.ui.favoriteteamlist

import com.example.footballleaguemvp.data.Team


/**
 * Created by ivanaazuka on 2020-01-06.
 * Android Engineer
 */
 
class FavoriteTeamContract {
    interface View {
        fun setupUi()
        fun setupNavigation()
        fun initializeData()
        fun populateData(teams: List<Team>)
    }
}