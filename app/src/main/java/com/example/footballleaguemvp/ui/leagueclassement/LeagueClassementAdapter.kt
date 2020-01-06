package com.example.footballleaguemvp.ui.leagueclassement

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.footballleaguemvp.R
import com.example.footballleaguemvp.data.Classement
import kotlinx.android.synthetic.main.item_classement_list.view.*


/**
 * Created by ivanaazuka on 2020-01-06.
 * Android Engineer
 */
 
class LeagueClassementAdapter (private val classements: List<Classement>) : RecyclerView.Adapter<LeagueClassementAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind (classement: Classement) {
            with(itemView){
                tvClassementTeamName.text = classement.name
                tvClassementMP.text = classement.played
                tvClassementW.text = classement.win
                tvClassementD.text = classement.draw
                tvClassementL.text = classement.loss
                tvClassementGF.text = classement.goalsfor
                tvClassementGA.text = classement.goalsagainst
                tvClassementGD.text = classement.goalsdifference
                tvClassementPts.text = classement.total
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_classement_list, parent, false))
    }

    override fun getItemCount() = classements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(classements[position])
    }

}
