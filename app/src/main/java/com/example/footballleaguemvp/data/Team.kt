package com.example.footballleaguemvp.data


/**
 * Created by ivanaazuka on 2019-11-29.
 * Android Engineer
 */
 
data class Team(
    var idTeam: String,
    var strTeam: String,
    var strTeamLogo: String,
    var strStadium: String,
    var strDescriptionEN: String)

data class TeamResponse(
    val teams: List<Team>
)