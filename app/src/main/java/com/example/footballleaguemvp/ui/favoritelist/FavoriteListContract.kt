package com.example.footballleaguemvp.ui.favoritelist


/**
 * Created by ivanaazuka on 2020-01-06.
 * Android Engineer
 */
 
class FavoriteListContract {

    interface View {
        fun setupUi()
        fun setupToolbar()
        fun setupNavigation()
        fun setupOnClickListener()
        fun loadSelectedTab()
    }
}