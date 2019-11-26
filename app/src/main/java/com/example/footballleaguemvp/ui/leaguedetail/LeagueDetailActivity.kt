package com.example.footballleaguemvp.ui.leaguedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.footballleaguemvp.R

class LeagueDetailActivity : AppCompatActivity() {

    companion object {
        const val TAG_LEAGUE_ID = "leagueId"
        const val TAG_LEAGUE_NAME = "leagueName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)
    }
}
