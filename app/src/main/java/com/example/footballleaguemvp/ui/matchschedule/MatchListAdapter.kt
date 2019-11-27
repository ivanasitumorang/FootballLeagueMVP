package com.example.footballleaguemvp.ui.matchschedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Match
import kotlinx.android.synthetic.main.item_match_list.view.*


/**
 * Created by ivanaazuka on 2019-11-27.
 * Android Engineer
 */

class MatchListAdapter (var matches: List<Match>, private val clickListener: MatchClickListener)
    : RecyclerView.Adapter<MatchListAdapter.ViewHolder>() {

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (item: Match, clickListener: MatchClickListener) {
            with(itemView){
                tvMatchName.text = item.strEvent
                tvDate.text = item.dateEvent
                tvTime.text = item.strTime
                tvHomeScore.text = item.intHomeScore
                tvHomeTeam.text = item.strHomeTeam
                tvAwayScore.text = item.intAwayScore
                tvAwayTeam.text = item.strAwayTeam
                itemMatch.setOnClickListener { clickListener.onClickLeagueItem(item.idEvent, item.strEvent) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match_list, parent, false))
    }

    override fun getItemCount() = matches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matches[position], clickListener)
    }
}

interface MatchClickListener {
    fun onClickLeagueItem(matchId: String, matchName: String)
}