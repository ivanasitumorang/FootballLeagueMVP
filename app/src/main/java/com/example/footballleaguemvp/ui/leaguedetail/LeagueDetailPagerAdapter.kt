package com.example.footballleaguemvp.ui.leaguedetail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.footballleaguemvp.ui.leagueclassement.LeagueClassementFragment
import com.example.footballleaguemvp.ui.leagueoverview.LeagueOverviewFragment
import com.example.footballleaguemvp.ui.matchschedule.MatchScheduleFragment
import com.example.footballleaguemvp.ui.teamlist.TeamListFragment


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class LeagueDetailPagerAdapter (fm: FragmentManager, private val leagueId: String) : FragmentStatePagerAdapter(fm) {

    companion object {
        const val TAG_TYPE_NEXT_MATCH = "next_match"
        const val TAG_TYPE_PREV_MATCH = "previous_match"
    }

    private val titles = arrayListOf(
        "Overview",
        "Prev Match",
        "Next Match",
        "Standing",
        "Team"
    )

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> LeagueOverviewFragment.newInstance(leagueId)
            1 -> MatchScheduleFragment.newInstance(TAG_TYPE_PREV_MATCH, leagueId)
            2 -> MatchScheduleFragment.newInstance(TAG_TYPE_NEXT_MATCH, leagueId)
            3 -> LeagueClassementFragment.newInstance(leagueId)
            else -> TeamListFragment.newInstance(leagueId)
        }
    }

    override fun getCount() = 5

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}