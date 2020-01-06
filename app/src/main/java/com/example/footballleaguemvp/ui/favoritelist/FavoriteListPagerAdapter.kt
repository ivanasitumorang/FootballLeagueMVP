package com.example.footballleaguemvp.ui.favoritelist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.footballleaguemvp.ui.favoritematchlist.FavoriteMatchFragment
import com.example.footballleaguemvp.ui.favoriteteamlist.FavoriteTeamFragment


/**
 * Created by ivanaazuka on 2020-01-06.
 * Android Engineer
 */
 
class FavoriteListPagerAdapter (fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val listTitles = arrayListOf("Match", "Teams")

    override fun getItem(position: Int): Fragment {
        return if (position == 0){
            FavoriteMatchFragment.newInstance()
        } else {
            FavoriteTeamFragment.newInstance()
        }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitles[position]
    }
}