package com.example.footballleaguemvp.ui.favoritematchlist

import com.example.footballleaguemvp.data.Match


/**
 * Created by ivanaazuka on 2019-12-06.
 * Android Engineer
 */
 
class FavoriteMatchListContract {
    interface View {
        fun setupUi()
        fun setupToolbar()
        fun setupNavigation()
        fun setupClickListener()
        fun initializeData()
        fun populateData(matches: List<Match>)
    }
}