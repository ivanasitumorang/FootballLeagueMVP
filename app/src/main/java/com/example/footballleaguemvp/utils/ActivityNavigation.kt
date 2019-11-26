package com.example.footballleaguemvp.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailActivity
import android.content.Intent
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailActivity.Companion.TAG_LEAGUE_ID
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailActivity.Companion.TAG_LEAGUE_NAME

/**
 * Created by ivanaazuka on 2019-11-26.
 * Android Engineer
 */
 
class ActivityNavigation constructor(private val activity: AppCompatActivity) {

    fun navigateToLeagueDetail(leagueId: String, leagueName: String){
        val leagueDetailPage = newIntent(activity, LeagueDetailActivity::class.java)
        leagueDetailPage.apply {
            putExtra(TAG_LEAGUE_ID, leagueId)
            putExtra(TAG_LEAGUE_NAME, leagueName)
        }
        activity.startActivity(leagueDetailPage)
    }

    private fun<T> newIntent(context: Context, cls: Class<T>): Intent {
        return Intent(context, cls)
    }
}