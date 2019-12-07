package com.example.footballleaguemvp.ui.favoritematchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.example.footballleaguemvp.utils.adapter.MatchClickListener
import com.example.footballleaguemvp.utils.adapter.MatchListAdapter
import com.example.footballleaguemvp.utils.databasehelper.database
import kotlinx.android.synthetic.main.activity_favorite_match_list.*
import kotlinx.android.synthetic.main.toolbar_activity.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchListActivity : AppCompatActivity(), FavoriteMatchListContract.View {

    private lateinit var activityNavigation: ActivityNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_match_list)

        setupUi()
        setupClickListener()
    }

    override fun onResume() {
        super.onResume()
        initializeData()
    }

    override fun setupUi() {
        setupNavigation()
        setupToolbar()
    }

    override fun setupToolbar() {
        btnToolbarBack.visibility = View.VISIBLE
        tvToolbarTitle.text = getString(R.string.favorite_match_list)
        btnFavoriteList.visibility = View.INVISIBLE
    }

    override fun setupClickListener() {
        btnToolbarBack.setOnClickListener { onBackPressed() }
    }

    override fun setupNavigation() {
        activityNavigation = ActivityNavigation(this)
    }

    override fun initializeData() {
        database.use {
            val result = select(Match.TABLE_FAVORITE_MATCH).parseList(classParser<Match>())
            populateData(result)
        }
    }

    override fun populateData(matches: List<Match>) {
        if (matches.isNullOrEmpty()){
            layoutNoData.visibility = View.VISIBLE
            rvFavoriteMatchList.visibility = View.GONE
        } else {
            val matchListAdapter =
                MatchListAdapter(
                    matches,
                    object :
                        MatchClickListener {
                        override fun onClickLeagueItem(matchId: String, matchName: String) {
                            activityNavigation.navigateToMatchDetail(matchId, matchName)
                        }
                    })

            rvFavoriteMatchList.adapter = matchListAdapter
        }
    }
}
