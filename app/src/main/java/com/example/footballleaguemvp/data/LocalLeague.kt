package com.example.footballleaguemvp.data

import android.os.Parcelable
import com.example.footballleaguemvp.R
import kotlinx.android.parcel.Parcelize


/**
 * Created by ivanaazuka on 2019-11-26.
 * Android Engineer
 */

@Parcelize
data class LocalLeague(
    val id: String,
    val logo: Int,
    val name: String) : Parcelable

object FootballLeagueData {
    val data  = mutableListOf (
        LocalLeague(
            "4328",
            R.drawable.english_premier_league,
            "English Premier League"),
        LocalLeague(
            "4334",
            R.drawable.french_ligue_1,
            "French Ligue 1"),
        LocalLeague(
            "4331",
            R.drawable.german_bundesliga,
            "German Bundesliga"),
        LocalLeague(
            "4332",
            R.drawable.italian_serie_a,
            "Italian Serie A"),
        LocalLeague(
            "4335",
            R.drawable.spanish_la_liga,
            "Spanish La Liga"),
        LocalLeague(
            "4346",
            R.drawable.american_mayor_league,
            "American Mayor League"),
        LocalLeague(
            "4344",
            R.drawable.portugeuese_premiera_liga,
            "Protugeuese Premiera Liga"),
        LocalLeague(
            "4356",
            R.drawable.australian_a_league,
            "Australian A League"),
        LocalLeague(
            "4330",
            R.drawable.scotish_premier_league,
            "Scotish Premier League"),
        LocalLeague(
            "4396",
            R.drawable.english_league_1,
            "English League 1")
    )
}