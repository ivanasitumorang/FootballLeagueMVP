package com.example.footballleaguemvp.ui.leagueoverview


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleaguemvp.R


class LeagueOverviewFragment : Fragment() {

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

        return inflater.inflate(R.layout.fragment_league_overview, container, false)
    }


}
