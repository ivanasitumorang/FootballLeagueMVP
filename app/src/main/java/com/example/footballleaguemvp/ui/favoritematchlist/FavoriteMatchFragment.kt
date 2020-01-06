package com.example.footballleaguemvp.ui.favoritematchlist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Match
import com.example.footballleaguemvp.ui.favoritelist.FavoriteListActivity
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.example.footballleaguemvp.utils.adapter.MatchClickListener
import com.example.footballleaguemvp.utils.adapter.MatchListAdapter
import com.example.footballleaguemvp.utils.databasehelper.database
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchFragment : Fragment(), FavoriteMatchContract.View {

    private lateinit var activityNavigation: ActivityNavigation

    companion object {
        fun newInstance(): FavoriteMatchFragment {
            return FavoriteMatchFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    override fun setupUi() {
        setupNavigation()
    }

    override fun setupNavigation() {
        activityNavigation = ActivityNavigation(activity as FavoriteListActivity)
    }

    override fun initializeData() {
        context!!.database.use {
            val result = select(Match.TABLE_FAVORITE_MATCH).parseList(classParser<Match>())
            populateData(result)
        }
    }

    override fun populateData(matches: List<Match>) {
        if (matches.isNullOrEmpty()){
            layoutNoData.visibility = View.VISIBLE
            rvFavoriteMatchList.visibility = View.GONE
        } else {
            val matchListAdapter =
                MatchListAdapter(
                    matches,
                    object :
                        MatchClickListener {
                        override fun onClickLeagueItem(matchId: String, matchName: String) {
                            activityNavigation.navigateToMatchDetail(matchId, matchName)
                        }
                    })

            rvFavoriteMatchList.adapter = matchListAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        initializeData()
    }

}
