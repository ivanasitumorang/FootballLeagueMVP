package com.example.footballleaguemvp.ui.matchdetail

import com.example.footballleaguemvp.data.Match


/**
 * Created by ivanaazuka on 2019-11-28.
 * Android Engineer
 */
 
class MatchDetailContract {
    interface View {
        fun setupUi()
        fun setupPresenter()
        fun setupToolbar(title: String)
        fun initializeData()
        fun displayData(match: Match)
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
    }
    interface Logic {
        fun getMatchDetail(matchId: String)
    }
}