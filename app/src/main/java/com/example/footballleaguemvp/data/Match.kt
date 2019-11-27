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
    val intHomeScore: String,
    val intAwayScore: String,
    val dateEvent: String,
    val strTime: String
)

data class MatchResponse(
    val events: List<Match>
)