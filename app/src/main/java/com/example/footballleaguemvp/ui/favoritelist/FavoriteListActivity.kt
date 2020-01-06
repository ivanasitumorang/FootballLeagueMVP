package com.example.footballleaguemvp.ui.favoritelist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_favorite_list.*
import kotlinx.android.synthetic.main.toolbar_activity.*

class FavoriteListActivity : AppCompatActivity(), FavoriteListContract.View {

    private lateinit var activityNavigation: ActivityNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_list)
        setupUi()
        setupOnClickListener()
    }

    override fun setupUi() {
        setupToolbar()
        setupNavigation()
        loadSelectedTab()
    }

    override fun setupToolbar() {
        btnToolbarBack.visibility = View.VISIBLE
        tvToolbarTitle.text = getString(R.string.favorite_list)
        btnFavoriteList.visibility = View.INVISIBLE
    }

    override fun setupNavigation() {
        activityNavigation = ActivityNavigation(this)
    }

    override fun setupOnClickListener() {
        btnToolbarBack.setOnClickListener { onBackPressed() }
    }

    override fun loadSelectedTab() {
        val pagerAdapter = FavoriteListPagerAdapter(supportFragmentManager)
        pagerFavoriteList.adapter = pagerAdapter
        tabLayoutFavoriteList.setupWithViewPager(pagerFavoriteList)
        tabLayoutFavoriteList.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                p0?.let {
                    pagerFavoriteList.currentItem = it.position
                }
            }

        })
    }
}
