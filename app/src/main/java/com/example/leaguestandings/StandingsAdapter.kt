package com.example.leaguestandings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguestandings.databinding.StandingsItemBinding

class StandingsAdapter(
     var teams: List<Standing>
) : RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder>() {

    inner class StandingsViewHolder(val binding: StandingsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        return StandingsViewHolder(StandingsItemBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false))
    }

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        holder.binding.apply {
            teamPlacement.text = teams[position].rank.toString()
            teamName.text = teams[position].team.name
            teamPlayed.text = (teams[position].home.played + teams[position].away.played).toString()
            teamWins.text = (teams[position].home.win + teams[position].away.win).toString()
            teamDraws.text = (teams[position].home.draw + teams[position].away.draw).toString()
            teamLosses.text = (teams[position].home.lose + teams[position].away.lose).toString()
            teamPoints.text = teams[position].points.toString()
        }
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}