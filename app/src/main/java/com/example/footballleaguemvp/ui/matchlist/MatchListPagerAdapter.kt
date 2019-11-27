package com.example.footballleaguemvp.ui.matchlist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.footballleaguemvp.ui.matchschedule.MatchScheduleFragment


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class MatchListPagerAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    companion object {
        const val TAG_TYPE_NEXT_MATCH = "next_match"
        const val TAG_TYPE_PREV_MATCH = "previous_match"
    }

    private val titles = arrayListOf(
        "Prev Match",
        "Next Match"
    )

    override fun getItem(position: Int): Fragment {
        return if (position == 0) MatchScheduleFragment.newInstance(TAG_TYPE_PREV_MATCH)
        else MatchScheduleFragment.newInstance(TAG_TYPE_NEXT_MATCH)
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}