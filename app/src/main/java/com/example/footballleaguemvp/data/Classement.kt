package com.example.footballleaguemvp.data


/**
 * Created by ivanaazuka on 2020-01-06.
 * Android Engineer
 */
 
data class Classement (
    var name: String,
    var played: String,
    var win: String? = "0",
    var draw: String? = "0",
    var loss: String? = "0",
    var total: String? = "0",
    var goalsfor: String? = "0",
    var goalsagainst: String? = "0",
    var goalsdifference: String? = "0"
)

data class ClassementResponse(
    val table: List<Classement>
)