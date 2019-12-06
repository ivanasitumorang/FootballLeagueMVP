package com.example.footballleaguemvp.ui.searchmatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.utils.adapter.MatchClickListener
import com.example.footballleaguemvp.utils.adapter.MatchListAdapter
import com.example.footballleaguemvp.utils.ActivityNavigation
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar_search.*
import kotlinx.android.synthetic.main.toolbar_search.btnToolbarBack

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
        with(searchView){
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

    }

    override fun hideLoadingIndicator() {

    }

    override fun setPresenter() {
        mPresenter = SearchPresenter(this)
    }

    override fun setupNavigation() {
        mActivityNavigation = ActivityNavigation(this)
    }

    override fun populateData(matches: List<Match>) {
        val matchListAdapter =
            MatchListAdapter(
                matches,
                object :
                    MatchClickListener {
                    override fun onClickLeagueItem(matchId: String, matchName: String) {
                        mActivityNavigation.navigateToMatchDetail(matchId, matchName)
                    }
                })
        rvMatchList.adapter = matchListAdapter
    }
}
