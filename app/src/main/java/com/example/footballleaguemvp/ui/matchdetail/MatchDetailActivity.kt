package com.example.footballleaguemvp.ui.matchdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.data.Team
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.toolbar_activity.*

class MatchDetailActivity : AppCompatActivity(), MatchDetailContract.View {

    companion object {
        const val TAG_MATCH_ID = "matchId"
        const val TAG_MATCH_NAME = "matchName"
    }

    private lateinit var mPresenter: MatchDetailPresenter
    private lateinit var mActivityNavigation: ActivityNavigation
    private var matchId = ""
    private var matchName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        val bundle = intent.extras
        if (bundle!=null){
            matchId = bundle.getString(TAG_MATCH_ID, "")
            matchName = bundle.getString(TAG_MATCH_NAME, "")
        }
        setupUi()
        setupClickListener()
        initializeData()
    }

    override fun setupUi() {
        setupToolbar(matchName)
        setupPresenter()
        setupNavigation()
    }

    override fun setupPresenter() {
        mPresenter = MatchDetailPresenter(this)
    }

    override fun setupToolbar(title: String) {
        btnToolbarBack.visibility = View.VISIBLE
        tvToolbarTitle.text = matchName
        btnToolbarBack.setOnClickListener { onBackPressed() }
    }

    override fun setupNavigation() {
        mActivityNavigation = ActivityNavigation(this)
    }

    override fun setupClickListener() {
    }

    override fun initializeData() {
        mPresenter.getMatchDetail(matchId)
    }

    override fun displayMatchDetail(match: Match, teamHomeId: String, teamAwayId: String) {
        mPresenter.getTeamDetail(teamHomeId, teamAwayId)
        tvDate.text = match.dateEvent
        tvTime.text = match.strTime
        tvHomeScore.text = match.intHomeScore ?: "-"
        tvAwayScore.text = match.intAwayScore ?: "-"
        tvHomeTeam.text = match.strHomeTeam
        tvAwayTeam.text = match.strAwayTeam
    }

    override fun displayHomeTeamDetail(team: Team) {
        Picasso.get().load(team.strTeamLogo).placeholder(resources.getDrawable(R.drawable.loading_animation)).into(ivHomeLogo)
    }

    override fun displayAwayTeamDetail(team: Team) {
        Picasso.get().load(team.strTeamLogo).placeholder(resources.getDrawable(R.drawable.loading_animation)).into(ivAwayLogo)
    }
    override fun showLoadingIndicator() {

    }

    override fun hideLoadingIndicator() {

    }
}
