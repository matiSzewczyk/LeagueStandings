package com.example.leaguestandings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguestandings.databinding.FragmentLeagueStandingsBinding
import com.example.leaguestandings.databinding.StandingsItemBinding

class StandingsAdapter(
    private var teams: List<Standing>
) : RecyclerView.Adapter<StandingsAdapter.StandingsViewHolder>() {

    inner class StandingsViewHolder(val binding: StandingsItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StandingsViewHolder {
        return StandingsViewHolder(StandingsItemBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false))
    }

    override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
        holder.apply {
            // TODO: 21.12.2021  
        }
    }

    override fun getItemCount(): Int {
        return teams.size
    }


}