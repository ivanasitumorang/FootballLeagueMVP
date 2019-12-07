package com.example.footballleaguemvp.ui.searchmatch

import com.example.footballleaguemvp.data.Match


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
        fun populateData(matches: List<Match>)
        fun setupSearchClickListener()
        fun showLoadingIndicator()
        fun hideLoadingIndicator()
        fun showNoData()
    }

    interface Logic {
        fun getSearchedData(query: String)
    }

}