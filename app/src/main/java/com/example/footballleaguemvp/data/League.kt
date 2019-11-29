package com.example.footballleaguemvp.data


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */
 
data class League(
    val idLeague: String,
    val strLeague: String,
    val strDescriptionEN: String,
    val strBanner: String,
    val strBadge: String,
    val strLogo: String
)

data class LeagueResponse(
    val leagues: List<League>
)