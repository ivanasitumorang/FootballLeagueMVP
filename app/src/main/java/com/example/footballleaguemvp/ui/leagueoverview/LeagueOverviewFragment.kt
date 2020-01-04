package com.example.footballleaguemvp.ui.leagueoverview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.League
import com.example.footballleaguemvp.network.AppNetworkServiceProvider
import com.example.footballleaguemvp.network.AppSchedulerProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_league_overview.*


class LeagueOverviewFragment : Fragment(), LeagueOverviewContract.View {

    private lateinit var leagueOverviewPresenter: LeagueOverviewPresenter

    private var leagueId = ""

    companion object {
        private const val TAG_LEAGUE_ID = "league_id"

        fun newInstance(leagueId: String): LeagueOverviewFragment {
            val bundle = Bundle()
            bundle.putString(TAG_LEAGUE_ID, leagueId)

            val fragment = LeagueOverviewFragment()
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
        return inflater.inflate(R.layout.fragment_league_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    override fun setUi() {
        setPresenter()
        initializeData()
    }

    override fun showLoadingIndicator() {
        ivOverviewLoadingIndicator.visibility = View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        ivOverviewLoadingIndicator.visibility = View.GONE
    }

    override fun setPresenter() {
        leagueOverviewPresenter = LeagueOverviewPresenter(this, AppSchedulerProvider(), AppNetworkServiceProvider())
    }

    override fun populateLeagueDetail(league: League) {
        Picasso.get().load(league.strBanner).placeholder(activity!!.applicationContext.resources.getDrawable(R.drawable.loading_animation)).into(ivLeagueOverviewDetail)
        tvLeagueOverviewDetail.text = league.strDescriptionEN
    }

    override fun initializeData() {
        leagueOverviewPresenter.fetchLeagueDetail(leagueId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        leagueOverviewPresenter.disposable.dispose()
    }

}
