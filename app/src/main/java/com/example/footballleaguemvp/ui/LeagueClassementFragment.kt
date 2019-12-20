package com.example.footballleaguemvp.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleaguemvp.R


class LeagueClassementFragment : Fragment() {

    companion object {
        private const val TAG_LEAGUE_ID = "league_id"

        fun newInstance(leagueId: String): LeagueClassementFragment {
            val bundle = Bundle()
            bundle.putString(TAG_LEAGUE_ID, leagueId)

            val fragment = LeagueClassementFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_league_classement, container, false)
    }


}
