package com.example.footballleaguemvp.ui.teamlist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleaguemvp.R

class TeamListFragment : Fragment() {

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


}
