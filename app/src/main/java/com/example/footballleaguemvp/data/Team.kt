package com.example.footballleaguemvp.data


/**
 * Created by ivanaazuka on 2019-11-29.
 * Android Engineer
 */
 
data class Team(
    val strTeam: String,
    val strTeamLogo: String)

data class TeamResponse(
    val teams: List<Team>
)