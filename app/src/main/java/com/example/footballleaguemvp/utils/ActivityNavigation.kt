package com.example.footballleaguemvp.utils

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.footballleaguemvp.data.LocalLeague
import com.example.footballleaguemvp.ui.favoritelist.FavoriteListActivity
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailActivity
import com.example.footballleaguemvp.ui.matchdetail.MatchDetailActivity
import com.example.footballleaguemvp.ui.matchlist.MatchListActivity
import com.example.footballleaguemvp.ui.searchmatch.SearchActivity
import com.example.footballleaguemvp.ui.teamdetail.TeamDetailActivity

/**
 * Created by ivanaazuka on 2019-11-26.
 * Android Engineer
 */
 
class ActivityNavigation constructor(private val activity: AppCompatActivity) {

    fun navigateToLeagueDetail(localLeague: LocalLeague){
        val leagueDetailPage = newIntent(activity, LeagueDetailActivity::class.java)
        leagueDetailPage.apply {
            putExtra(LeagueDetailActivity.TAG_LOCAL_LEAGUE, localLeague)
        }
        activity.startActivity(leagueDetailPage)
    }

    fun navigateToMatchList(leagueId: String, leagueName: String){
        val matchListPage = newIntent(activity, MatchListActivity::class.java)
        matchListPage.apply {
            putExtra(MatchListActivity.TAG_LEAGUE_ID, leagueId)
            putExtra(MatchListActivity.TAG_LEAGUE_NAME, leagueName)
        }
        activity.startActivity(matchListPage)
    }

    fun navigateToFavoriteMatchList(){
        val favoriteListPage = newIntent(activity, FavoriteListActivity::class.java)
        activity.startActivity(favoriteListPage)
    }

    fun navigateToMatchDetail(matchId: String, matchName: String){
        val matchDetailPage = newIntent(activity, MatchDetailActivity::class.java)
        matchDetailPage.apply {
            putExtra(MatchDetailActivity.TAG_MATCH_ID, matchId)
            putExtra(MatchDetailActivity.TAG_MATCH_NAME, matchName)
        }
        activity.startActivity(matchDetailPage)
    }

    fun navigateToTeamDetail(teamId: String, teamName: String){
        val teamDetailPage = newIntent(activity, TeamDetailActivity::class.java)
        teamDetailPage.apply {
            putExtra(TeamDetailActivity.TAG_TEAM_ID, teamId)
            putExtra(TeamDetailActivity.TAG_TEAM_NAME, teamName)
        }
        activity.startActivity(teamDetailPage)
    }

    fun navigateToSearchPage(){
        val searchPage = newIntent(activity, SearchActivity::class.java)
        activity.startActivity(searchPage)
    }

    private fun<T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }
}