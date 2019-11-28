package com.example.footballleaguemvp.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailActivity
import android.content.Intent
import com.example.footballleaguemvp.ui.matchdetail.MatchDetailActivity
import com.example.footballleaguemvp.ui.matchlist.MatchListActivity
import com.example.footballleaguemvp.ui.searchmatch.SearchActivity

/**
 * Created by ivanaazuka on 2019-11-26.
 * Android Engineer
 */
 
class ActivityNavigation constructor(private val activity: AppCompatActivity) {

    fun navigateToLeagueDetail(leagueId: String, leagueName: String){
        val leagueDetailPage = newIntent(activity, LeagueDetailActivity::class.java)
        leagueDetailPage.apply {
            putExtra(LeagueDetailActivity.TAG_LEAGUE_ID, leagueId)
            putExtra(LeagueDetailActivity.TAG_LEAGUE_NAME, leagueName)
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

    fun navigateToMatchDetail(matchId: String, matchName: String){
        val matchDetailPage = newIntent(activity, MatchDetailActivity::class.java)
        matchDetailPage.apply {
            putExtra(MatchDetailActivity.TAG_MATCH_ID, matchId)
            putExtra(MatchDetailActivity.TAG_MATCH_NAME, matchName)
        }
        activity.startActivity(matchDetailPage)
    }

    fun navigateToSearchPage(){
        val searchPage = newIntent(activity, SearchActivity::class.java)
        activity.startActivity(searchPage)
    }

    private fun<T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }
}