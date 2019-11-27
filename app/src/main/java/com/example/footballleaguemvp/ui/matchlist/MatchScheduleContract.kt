package com.example.footballleaguemvp.ui.matchlist


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class MatchScheduleContract {
    interface View {
        fun setupUi()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
    }

    interface Logic {
        fun getMatchList(type: String)
    }
}