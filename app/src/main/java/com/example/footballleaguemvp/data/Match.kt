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
) {
    companion object {
        const val TABLE_FAVORITE_MATCH = "TABLE_FAVORITE_MATCH"
        const val EVENT_ID = "EVENT_ID"
        const val EVENT_NAME = "EVENT_NAME"
        const val EVENT_HOME_TEAM = "EVENT_HOME_TEAM"
        const val EVENT_AWAY_TEAM = "EVENT_AWAY_TEAM"
        const val EVENT_HOME_SCORE = "EVENT_HOME_SCORE"
        const val EVENT_AWAY_SCORE = "EVENT_AWAY_SCORE"
        const val EVENT_DATE = "EVENT_DATE"
        const val EVENT_TIME = "EVENT_TIME"
        const val EVENT_ID_HOME_TEAM = "EVENT_ID_HOME_TEAM"
        const val EVENT_ID_AWAY_TEAM = "EVENT_ID_AWAY_TEAM"
        const val EVENT_SPORT_NAME = "EVENT_SPORT_NAME"

    }
}

data class MatchResponse(
    val events: List<Match>
)

data class SearchMatchResponse(
    val event: List<Match>
)