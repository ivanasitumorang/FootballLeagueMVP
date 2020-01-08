package com.example.footballleaguemvp.ui.searchmatch


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
class SearchContract {
    interface View {
        fun setupUi()
        fun setupToolbar()
        fun setPresenter()
        fun setupNavigation()
        fun populateData(data: List<Any>)
        fun setupSearchClickListener()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showNoData()
    }

    interface Logic {
        fun getSearchedMatch(query: String)
        fun getSearchedTeam(query: String)
    }

}