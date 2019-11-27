package com.example.footballleaguemvp.ui.matchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.footballleaguemvp.R

class MatchListActivity : AppCompatActivity() {

    companion object {
        const val TAG_LEAGUE_ID = "leagueId"
        const val TAG_LEAGUE_NAME = "leagueName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_list)
    }
}
