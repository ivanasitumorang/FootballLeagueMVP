package com.example.footballleaguemvp.ui.teamdetail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Team
import com.example.footballleaguemvp.network.AppNetworkServiceProvider
import com.example.footballleaguemvp.network.AppSchedulerProvider
import com.example.footballleaguemvp.utils.ActivityNavigation
import com.example.footballleaguemvp.utils.databasehelper.database
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import kotlinx.android.synthetic.main.toolbar_activity.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class TeamDetailActivity : AppCompatActivity(), TeamDetailContract.View {

    companion object {
        const val TAG_TEAM_ID = "team_id"
        const val TAG_TEAM_NAME = "team_name"
    }

    private lateinit var teamDetailPresenter: TeamDetailPresenter
    private lateinit var activityNavigation: ActivityNavigation
    private lateinit var team : Team
    private var teamId = ""
    private var teamName = ""
    private var isFavoriteTeam = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)
        val bundle = intent.extras
        if (bundle!=null){
            teamId = bundle.getString(TAG_TEAM_ID, "")
            teamName = bundle.getString(TAG_TEAM_NAME, "")
        }
        setupUi()
        setupClickListener()
        initializeData()
    }

    override fun setupUi() {
        setupPresenter()
        setupToolbar(teamName)
        toggleFavoriteIcon()
    }

    override fun setupPresenter() {
        teamDetailPresenter = TeamDetailPresenter(this, AppSchedulerProvider(), AppNetworkServiceProvider())
    }

    override fun setupToolbar(title: String) {
        btnToolbarBack.visibility = View.VISIBLE
        tvToolbarTitle.text = title
        btnFavoriteList.visibility = View.GONE
        btnFavorite.visibility = View.VISIBLE
        val params = btnFavorite.layoutParams as RelativeLayout.LayoutParams
        params.addRule(RelativeLayout.ALIGN_PARENT_END)
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)

        btnFavoriteList.layoutParams = params
    }

    override fun initializeData() {
        teamDetailPresenter.getTeamDetail(teamId)
    }

    override fun displayTeamDetail(team: Team) {
        this.team = team
        Picasso.get().load(team.strTeamLogo).placeholder(resources.getDrawable(R.drawable.loading_animation)).into(ivTeamDetailLogo)
        tvTeamDetailName.text = team.strTeam
        tvTeamDetailStadium.text = getString(R.string.format_stadium_team_detail, team.strStadium)
        tvTeamDetailDesc.text = team.strDescriptionEN
    }

    override fun setupClickListener() {
        btnToolbarBack.setOnClickListener {
            finish()
        }

        btnFavorite.setOnClickListener {
            if (::team.isInitialized){
                if (isFavoriteTeam){
                    removeTeamFromFavorite(team)
                } else {
                    addTeamToFavorite(team)
                }
            }
        }
    }

    override fun setupNavigation() {
        activityNavigation = ActivityNavigation(this)
    }

    override fun addTeamToFavorite(team: Team) {
        Toast.makeText(applicationContext, "Add $teamName", Toast.LENGTH_SHORT).show()
        try {
            database.use {
                insert(Team.TABLE_FAVORITE_TEAM,
                    Team.TEAM_ID to team.idTeam,
                    Team.TEAM_NAME to team.strTeam,
                    Team.TEAM_LOGO_URL to team.strTeamLogo,
                    Team.TEAM_STADIUM to team.strStadium,
                    Team.TEAM_DESCRIPTION to team.strDescriptionEN,
                    Team.TEAM_SPORT to team.strSport)
            }
            toast("${team.strTeam} has been added to favorite list").show()
            toggleFavoriteIcon()
        } catch (e: SQLiteConstraintException){
            toast("Fail to add team to favorite list").show()
        }
    }

    override fun removeTeamFromFavorite(team: Team) {
        Toast.makeText(applicationContext, "Remove $teamName", Toast.LENGTH_SHORT).show()
        try {
            database.use {
                delete(Team.TABLE_FAVORITE_TEAM, "(${Team.TEAM_ID} = {idTeam})",
                    "idTeam" to teamId)
            }
            toast("${team.strTeam} has been removed from favorite list").show()
            toggleFavoriteIcon()
        } catch (e: SQLiteConstraintException){
            toast("Fail to remove team from favorite list").show()
        }
    }

    override fun toggleFavoriteIcon() {
        database.use {
            val result = select(Team.TABLE_FAVORITE_TEAM)
                .whereArgs("(${Team.TEAM_ID} = {idTeam})",
                    "idTeam" to teamId)
            val favorite = result.parseList(classParser<Team>())
            isFavoriteTeam = favorite.isNotEmpty()
        }

        if (isFavoriteTeam){
            btnFavorite.setImageResource(R.drawable.ic_favorite_full)
        } else {
            btnFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }

    override fun showLoadingIndicator() {
        ivTeamDetailLoadingIndicator.visibility =  View.VISIBLE
    }

    override fun hideLoadingIndicator() {
        ivTeamDetailLoadingIndicator.visibility =  View.GONE
    }
}
