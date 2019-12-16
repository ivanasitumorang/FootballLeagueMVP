package com.example.footballleaguemvp.ui.matchschedule


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.network.AppSchedulerProvider
import com.example.footballleaguemvp.ui.matchlist.MatchListActivity
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.example.footballleaguemvp.utils.adapter.MatchClickListener
import com.example.footballleaguemvp.utils.adapter.MatchListAdapter
import kotlinx.android.synthetic.main.fragment_match_list.*


class MatchScheduleFragment : Fragment(), MatchScheduleContract.View {

    private var tabtype  = ""
    private var leagueId  = ""
    private lateinit var mPresenter: MatchSchedulePresenter
    private lateinit var mActivityNavigation: ActivityNavigation

    companion object {
        const val TAG_MATCH_TYPE = "match_type"
        const val TAG_LEAGUE_ID = "league_id"

        fun newInstance(type: String, leagueId: String): MatchScheduleFragment {
            val bundle = Bundle()
            bundle.putString(TAG_MATCH_TYPE, type)
            bundle.putString(TAG_LEAGUE_ID, leagueId)

            val fragment =
                MatchScheduleFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        if (bundle != null) {
            tabtype = bundle.getString(TAG_MATCH_TYPE, "")
            leagueId = bundle.getString(TAG_LEAGUE_ID, "")
        }
        return inflater.inflate(R.layout.fragment_match_list, container, false)
    }

    override fun setupNavigation() {
        mActivityNavigation = ActivityNavigation(activity as MatchListActivity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        initializeData()
    }

    override fun setupUi() {
        setupPresenter()
        setupNavigation()
    }

    override fun setupPresenter() {
        mPresenter = MatchSchedulePresenter(this, AppSchedulerProvider())
    }

    override fun initializeData() {
        mPresenter.getMatchList(tabtype, leagueId)
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

            rvMatchList.adapter = matchListAdapter
        }
    }

    override fun showLoadingIndicator() {
        ivLoadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        ivLoadingIndicator.visibility = View.GONE
    }

    override fun showNoData() {
        layoutNoData.visibility = View.VISIBLE
        rvMatchList.visibility = View.GONE
    }
}
