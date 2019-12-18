package com.example.footballleaguemvp.ui.searchmatch

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.network.AppNetworkServiceProvider
import com.example.footballleaguemvp.network.AppSchedulerProvider
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.example.footballleaguemvp.utils.adapter.MatchClickListener
import com.example.footballleaguemvp.utils.adapter.MatchListAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar_search.*

class SearchActivity : AppCompatActivity(), SearchContract.View {

    private lateinit var mActivityNavigation: ActivityNavigation
    private lateinit var mPresenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setupUi()
        setupSearchClickListener()
    }

    override fun setupUi() {
        setupToolbar()
        setPresenter()
        setupNavigation()
    }

    override fun setupToolbar() {
        btnToolbarBack.setOnClickListener { onBackPressed() }
    }

    override fun setupSearchClickListener() {
        with(searchViewEdit){
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (!query.isNullOrEmpty()){
                        mPresenter.getSearchedData(query)
                    }

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (!newText.isNullOrEmpty()){
                        mPresenter.getSearchedData(newText)
                    }
                    return true
                }

            })
        }
    }

    override fun showLoadingIndicator() {
        ivLoadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        ivLoadingIndicator.visibility = View.GONE
    }

    override fun setPresenter() {
        mPresenter = SearchPresenter(this, AppSchedulerProvider(), AppNetworkServiceProvider())
    }

    override fun setupNavigation() {
        mActivityNavigation = ActivityNavigation(this)
    }

    override fun populateData(matches: List<Match>) {
        if (matches.isNullOrEmpty()){
            showNoData()
        } else {
            val matchListAdapter =
                MatchListAdapter(
                    matches,
                    object :
                        MatchClickListener {
                        override fun onClickLeagueItem(matchId: String, matchName: String) {
                            mActivityNavigation.navigateToMatchDetail(matchId, matchName)
                        }
                    })
            rvMatchListSearch.adapter = matchListAdapter
        }
    }

    override fun showNoData() {
        layoutNoDataSearch.visibility = View.VISIBLE
        rvMatchListSearch.visibility = View.GONE
    }
}
