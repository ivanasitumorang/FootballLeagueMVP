package com.example.footballleaguemvp.ui.teamlist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Team
import com.example.footballleaguemvp.network.AppNetworkServiceProvider
import com.example.footballleaguemvp.network.AppSchedulerProvider
import com.example.footballleaguemvp.ui.leaguedetail.LeagueDetailActivity
import com.example.footballleaguemvp.utils.ActivityNavigation
import kotlinx.android.synthetic.main.fragment_team_list.*

class TeamListFragment : Fragment(), TeamListContract.View {

    private lateinit var teamListPresenter: TeamListPresenter
    private lateinit var activityNavigation: ActivityNavigation
    private var leagueId = ""

    companion object {
        private const val TAG_LEAGUE_ID = "league_id"

        fun newInstance(leagueId: String): TeamListFragment {
            val bundle = Bundle()
            bundle.putString(TAG_LEAGUE_ID, leagueId)

            val fragment = TeamListFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bundle = arguments
        if (bundle != null){
            leagueId = bundle.getString(TAG_LEAGUE_ID, "")
        }

        return inflater.inflate(R.layout.fragment_team_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    override fun setupUi() {
        setupPresenter()
        setupNavigation()
        initializeData()
    }

    override fun setupPresenter() {
        teamListPresenter = TeamListPresenter(this, AppSchedulerProvider(), AppNetworkServiceProvider())
    }

    override fun setupNavigation() {
        activityNavigation = ActivityNavigation(activity as LeagueDetailActivity)
    }

    override fun initializeData() {
        teamListPresenter.getTeamList(leagueId)
    }

    override fun populateData(teams: List<Team>) {
        if (teams.isNullOrEmpty()){
            showNoData()
        } else {
            val teamListAdapter = TeamListAdapter(activity!!.applicationContext, teams, object : TeamClickListener {
                override fun onClickTeamItem(teamId: String, teamName: String) {
                    activityNavigation.navigateToTeamDetail(teamId, teamName)
                }
            })
            rvTeamList.adapter = teamListAdapter
        }
    }

    override fun showLoadingIndicator() {
        ivTeamListLoadingIndicator?.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        ivTeamListLoadingIndicator?.visibility = View.GONE
    }

    override fun showNoData() {
        layoutNoData.visibility = View.VISIBLE
        rvTeamList.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        teamListPresenter.mDisposable.dispose()
    }
}
