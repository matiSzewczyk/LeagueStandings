package com.example.leaguestandings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguestandings.databinding.FragmentLeagueStandingsBinding

class LeagueStandingsFragment : Fragment(R.layout.fragment_league_standings) {

    private lateinit var binding: FragmentLeagueStandingsBinding

    private lateinit var standingsAdapter: StandingsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLeagueStandingsBinding.bind(view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = binding.myRecyclerView.apply {
        standingsAdapter = StandingsAdapter()
        adapter = standingsAdapter
        layoutManager = LinearLayoutManager(context)
    }
}