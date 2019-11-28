package com.example.footballleaguemvp.ui.leaguedetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.League
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_league_detail.*
import kotlinx.android.synthetic.main.toolbar_activity.*

class LeagueDetailActivity : AppCompatActivity(), LeagueDetailContract.View {

    companion object {
        const val TAG_LEAGUE_ID = "leagueId"
        const val TAG_LEAGUE_NAME = "leagueName"
    }

    private lateinit var mPresenter: LeagueDetailPresenter
    private lateinit var mActivityNavigation: ActivityNavigation
    private var leagueId = ""
    private var leagueName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)
        val bundle = intent.extras
        if (bundle!=null){
            leagueId = bundle.getString(TAG_LEAGUE_ID, "")
            leagueName = bundle.getString(TAG_LEAGUE_NAME, "")
        }
        setUi()
        initializeData()
    }

    override fun setPresenter() {
        mPresenter = LeagueDetailPresenter(this)
    }

    override fun setUi() {
        setupToolbar(leagueName)
        setPresenter()
        setupNavigation()
        setupClickListener()
    }

    override fun setupToolbar(title: String) {
        btnToolbarBack.visibility = View.VISIBLE
        tvToolbarTitle.text = leagueName
        btnToolbarBack.setOnClickListener { onBackPressed() }
        searchView.visibility = View.VISIBLE
    }

    override fun setupNavigation() {
        mActivityNavigation = ActivityNavigation(this)
    }

    override fun initializeData() {
        mPresenter.setLeagueDetail(leagueId)
    }

    override fun populateData(league: League) {
        Picasso.get()
            .load(league.strBanner)
            .into(ivLeagueBanner)
        tvLeagueName.text = league.strLeague
        tvLeagueDetail.text = league.strDescriptionEN
    }

    override fun showLoadingIndicator() {
        ivLoadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        ivLoadingIndicator.visibility = View.GONE
    }

    override fun enableButtonSeeMatch() {
        btnSeeMatch.isEnabled = true
    }

    override fun disableButtonSeeMatch() {
        btnSeeMatch.isEnabled = false
    }

    override fun setupClickListener() {
        searchView.setOnClickListener {
            mActivityNavigation.navigateToSearchPage()
        }

        btnSeeMatch.setOnClickListener {
            mActivityNavigation.navigateToMatchList(leagueId, leagueName)
        }
    }
}
