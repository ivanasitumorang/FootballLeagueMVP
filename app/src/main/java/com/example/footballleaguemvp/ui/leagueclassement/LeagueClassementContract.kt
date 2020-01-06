package com.example.footballleaguemvp.ui.leagueclassement

import com.example.footballleaguemvp.data.Classement


/**
 * Created by ivanaazuka on 2020-01-06.
 * Android Engineer
 */
 
class LeagueClassementContract {
    interface View {
        fun setupUi()
        fun setupPresenter()
        fun initializeData()
        fun populateData(classements: List<Classement>)
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showNoData()
    }

    interface Logic {
        fun getClassements(leagueId: String)
    }
}