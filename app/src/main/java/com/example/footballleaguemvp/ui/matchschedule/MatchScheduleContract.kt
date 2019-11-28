package com.example.footballleaguemvp.ui.matchschedule

import com.example.footballleaguemvp.data.Match


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class MatchScheduleContract {
    interface View {
        fun setupUi()
        fun setupPresenter()
        fun setupNavigation()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun initializeData()
        fun populateData(matches: List<Match>)
    }

    interface Logic {
        fun getMatchList(type: String, leagueId: String)
    }
}