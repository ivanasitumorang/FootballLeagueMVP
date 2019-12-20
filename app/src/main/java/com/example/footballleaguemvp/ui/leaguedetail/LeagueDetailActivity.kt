package com.example.footballleaguemvp.ui.leaguedetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.ui.matchlist.MatchListPagerAdapter
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_league_detail.*
import kotlinx.android.synthetic.main.toolbar_activity.*

class LeagueDetailActivity : AppCompatActivity(), LeagueDetailContract.View {

    companion object {
        const val TAG_LEAGUE_ID = "leagueId"
        const val TAG_LEAGUE_NAME = "leagueName"
    }

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
        setupClickListener()
    }

    override fun setUi() {
        setupToolbar(leagueName)
        setupNavigation()
        loadSelectedTab()
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

    override fun setupClickListener() {
        searchView.setOnClickListener {
            mActivityNavigation.navigateToSearchPage()
        }

        btnFavoriteList.setOnClickListener {
            mActivityNavigation.navigateToFavoriteMatchList()
        }
    }

    override fun loadSelectedTab() {
        val pagerAdapter = MatchListPagerAdapter(supportFragmentManager, leagueId)
        pager.adapter = pagerAdapter
        tabLayout.setupWithViewPager(pager)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.let {
                    pager.currentItem = it.position
                }
            }

        })
    }
}
