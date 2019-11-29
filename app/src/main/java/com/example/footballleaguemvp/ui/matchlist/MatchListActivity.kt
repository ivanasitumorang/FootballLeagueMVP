package com.example.footballleaguemvp.ui.matchlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_match_list.*
import kotlinx.android.synthetic.main.toolbar_activity.*

class MatchListActivity : AppCompatActivity(), MatchListContract.View {

    companion object {
        const val TAG_LEAGUE_ID = "leagueId"
        const val TAG_LEAGUE_NAME = "leagueName"
    }

    private var leagueId = ""
    private var leagueName = ""
    private lateinit var mActivityNavigation: ActivityNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_list)
        val bundle = intent.extras
        if (bundle!=null){
            leagueId = bundle.getString(TAG_LEAGUE_ID, "")
            leagueName = bundle.getString(TAG_LEAGUE_NAME, "")
        }
        setupUi()
        setClickListener()
    }

    override fun setupUi() {
        setupToolbar(leagueName)
        setupNavigation()
        loadSelectedTab()
    }

    override fun setupToolbar(title: String) {
        btnToolbarBack.visibility = View.VISIBLE
        tvToolbarTitle.text = leagueName
        btnToolbarBack.setOnClickListener { onBackPressed() }
    }

    override fun setupNavigation() {
        mActivityNavigation = ActivityNavigation(this)
    }

    override fun setClickListener() {
    }

    override fun loadSelectedTab() {
        val matchListPagerAdapter = MatchListPagerAdapter(supportFragmentManager, leagueId)
        pager.adapter = matchListPagerAdapter
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
