package com.example.footballleaguemvp.ui.leaguelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.LocalLeague
import com.example.footballleaguemvp.utils.ActivityNavigation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_activity.*

class LeagueListActivity : AppCompatActivity(), LeagueListContract.View {

    private lateinit var mPresenter: LeagueListPresenter
    private lateinit var mAdapter: LeagueListAdapter
    private lateinit var mActivityNavigation: ActivityNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        initializeData()
    }

    override fun setPresenter() {
        mPresenter = LeagueListPresenter(this)
    }

    override fun setupUi() {
        setPresenter()
        setupToolbar()
        setupNavigation()
    }

    override fun setupToolbar() {
        tvToolbarTitle.text = getString(R.string.app_name)
    }

    override fun setupNavigation() {
        mActivityNavigation = ActivityNavigation(this)
    }

    override fun populateData(leagueList: List<LocalLeague>) {
        mAdapter = LeagueListAdapter(
            context = applicationContext,
            leagues = leagueList,
            clickListener = object : LeagueClickListener {
                override fun onClickLeagueItem(leagueId: String, leagueName: String) {
                    mActivityNavigation.navigateToLeagueDetail(leagueId, leagueName)
                }

            })
        rvLeagueList.adapter = mAdapter
    }

    override fun initializeData() {
        mPresenter.setData()
    }
}
