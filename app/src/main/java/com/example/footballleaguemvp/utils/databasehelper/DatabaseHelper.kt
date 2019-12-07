package com.example.footballleaguemvp.utils.databasehelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.footballleaguemvp.data.Match
import org.jetbrains.anko.db.*


/**
 * Created by ivanaazuka on 2019-12-06.
 * Android Engineer
 */
 
class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteMatchDb", null, 2) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.applicationContext)
            }
            return instance as DatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(Match.TABLE_FAVORITE_MATCH, true,
            Match.EVENT_ID to TEXT + PRIMARY_KEY,
            Match.EVENT_NAME to TEXT,
            Match.EVENT_HOME_TEAM to TEXT,
            Match.EVENT_AWAY_TEAM to TEXT,
            Match.EVENT_HOME_SCORE to TEXT,
            Match.EVENT_AWAY_SCORE to TEXT,
            Match.EVENT_DATE to TEXT,
            Match.EVENT_TIME to TEXT,
            Match.EVENT_ID_HOME_TEAM to TEXT,
            Match.EVENT_ID_AWAY_TEAM to TEXT,
            Match.EVENT_SPORT_NAME to TEXT,
            Match.EVENT_HOME_GOAL to TEXT,
            Match.EVENT_HOME_REDCARD to TEXT,
            Match.EVENT_HOME_YELLOWCARD to TEXT,
            Match.EVENT_AWAY_GOAL to TEXT,
            Match.EVENT_AWAY_REDCARD to TEXT,
            Match.EVENT_AWAY_YELLOWCARD to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Match.TABLE_FAVORITE_MATCH, true)
    }
}

val Context.database: DatabaseHelper
 get() = DatabaseHelper.getInstance(applicationContext)