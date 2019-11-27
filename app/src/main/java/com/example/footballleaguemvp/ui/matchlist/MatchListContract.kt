package com.example.footballleaguemvp.ui.matchlist


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class MatchListContract {
    interface View {
        fun setupUi()
        fun setupToolbar(title: String)
        fun loadSelectedTab()
        fun setClickListener()
        fun setupNavigation()
    }
}