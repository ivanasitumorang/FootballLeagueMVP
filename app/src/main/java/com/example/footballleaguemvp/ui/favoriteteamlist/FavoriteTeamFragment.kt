package com.example.footballleaguemvp.ui.favoriteteamlist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Team
import com.example.footballleaguemvp.ui.favoritelist.FavoriteListActivity
import com.example.footballleaguemvp.ui.teamlist.TeamClickListener
import com.example.footballleaguemvp.ui.teamlist.TeamListAdapter
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.example.footballleaguemvp.utils.databasehelper.database
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamFragment : Fragment(), FavoriteTeamContract.View {

    private lateinit var activityNavigation: ActivityNavigation

    companion object {
        fun newInstance(): FavoriteTeamFragment {
            return FavoriteTeamFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
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
            val result = select(Team.TABLE_FAVORITE_TEAM).parseList(classParser<Team>())
            populateData(result)
        }
    }

    override fun populateData(teams: List<Team>) {
        if (teams.isNullOrEmpty()){
            layoutNoData.visibility = View.VISIBLE
            rvFavoriteTeamList.visibility = View.GONE
        } else {
            val teamListAdapter =
                TeamListAdapter(
                    context!!,
                    teams,
                    object :
                        TeamClickListener {
                        override fun onClickTeamItem(teamId: String, teamName: String) {
                            activityNavigation.navigateToTeamDetail(teamId, teamName)
                        }
                    })

            rvFavoriteTeamList.adapter = teamListAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        initializeData()
    }

}
