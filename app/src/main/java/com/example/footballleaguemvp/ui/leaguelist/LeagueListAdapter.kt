package com.example.footballleaguemvp.ui.leaguelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.LocalLeague
import kotlinx.android.synthetic.main.item_league_list.view.*


/**
 * Created by ivanaazuka on 2019-11-26.
 * Android Engineer
 */
 
class LeagueListAdapter (private val context: Context, var leagues: List<LocalLeague>, private val clickListener: LeagueClickListener)
    : RecyclerView.Adapter<LeagueListAdapter.ViewHolder>() {

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (item: LocalLeague, clickListener: LeagueClickListener) {
            with(itemView){
                imageView.setImageResource(item.logo)
                tvLeagueName.text = item.name
                itemLeague.setOnClickListener { clickListener.onClickLeagueItem(item.id, item.name) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_league_list, null))
    }

    override fun getItemCount() = leagues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(leagues[position], clickListener)
    }
}

interface LeagueClickListener {
    fun onClickLeagueItem(leagueId: String, leagueName: String)
}