package com.example.footballleaguemvp.data


/**
 * Created by ivanaazuka on 2019-11-29.
 * Android Engineer
 */
 
data class Team(
    var idTeam: String,
    var strTeam: String,
    var strTeamLogo: String? = null,
    var strStadium: String,
    var strDescriptionEN: String) {

    companion object {
        const val TABLE_FAVORITE_TEAM = "TABLE_FAVORITE_TEAM"
        const val TEAM_ID = "EVENT_ID"
        const val TEAM_NAME = "EVENT_NAME"
        const val TEAM_LOGO_URL = "TEAM_LOGO_URL"
        const val TEAM_STADIUM = "TEAM_STADIUM"
        const val TEAM_DESCRIPTION = "TEAM_DESCRIPTION"
    }
}

data class TeamResponse(
    val teams: List<Team>
)