package com.example.footballleaguemvp.ui.teamlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Team
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_team_list.view.*


/**
 * Created by ivanaazuka on 2020-01-03.
 * Android Engineer
 */
 

class TeamListAdapter (private val context: Context, private val teams: List<Team>, private val clickListener: TeamClickListener) : RecyclerView.Adapter<TeamListAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (context: Context, team: Team, clickListener: TeamClickListener) {
            itemView.tvTeamName.text = team.strTeam
            Picasso.get().load(team.strTeamLogo).placeholder(context.resources.getDrawable(R.drawable.loading_animation)).into(itemView.ivTeamLogo)
            itemView.llTeamItem.setOnClickListener { clickListener.onClickTeamItem(team.idTeam) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team_list, parent, false))
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, teams[position], clickListener)
    }

}

interface TeamClickListener {
    fun onClickTeamItem(teamId: String)
}
