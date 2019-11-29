package com.example.footballleaguemvp.data


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */

data class Match(
    val idEvent: String,
    val strEvent: String,
    val strHomeTeam: String,
    val strAwayTeam: String,
    val intHomeScore: String? = null,
    val intAwayScore: String? = null,
    val dateEvent: String,
    val strTime: String,
    val idHomeTeam: String,
    val idAwayTeam: String,
    val strSport: String
)

data class MatchResponse(
    val events: List<Match>
)

data class SearchMatchResponse(
    val event: List<Match>
)