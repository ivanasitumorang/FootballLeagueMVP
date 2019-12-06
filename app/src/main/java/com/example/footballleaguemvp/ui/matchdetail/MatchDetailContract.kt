package com.example.footballleaguemvp.ui.matchdetail

import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.data.Team


/**
 * Created by ivanaazuka on 2019-11-28.
 * Android Engineer
 */
 
class MatchDetailContract {
    interface View {
        fun setupUi()
        fun setupPresenter()
        fun setupToolbar(title: String)
        fun initializeData()
        fun displayMatchDetail(match: Match, teamHomeId: String, teamAwayId: String)
        fun displayHomeTeamDetail(team: Team)
        fun displayAwayTeamDetail(team: Team)
        fun setupClickListener()
        fun setupNavigation()
        fun addMatchToFavorite(match: Match)
        fun removeMatchToFavorite(match: Match)
        fun toggleFavoriteIcon()
    }
    interface Logic {
        fun getMatchDetail(matchId: String)
        fun getTeamDetail(teamHomeId: String, teamAwayId: String)
    }
}