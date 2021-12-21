package com.example.leaguestandings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguestandings.databinding.FragmentLeagueStandingsBinding

const val LEAGUE = 39
const val SEASON = 2020

class LeagueStandingsFragment : Fragment(R.layout.fragment_league_standings) {

    private lateinit var binding: FragmentLeagueStandingsBinding

    private lateinit var standingsAdapter: StandingsAdapter
    private val standingsViewModel: StandingsViewModel by viewModels()
    private lateinit var teams: List<Standing>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLeagueStandingsBinding.bind(view)

        val standingsObserver = Observer<List<Standing>> { newTeams ->
            teams = newTeams
        }

        lifecycleScope.launchWhenCreated {
            standingsViewModel.getStandings(LEAGUE, SEASON)
            teams = standingsViewModel.standings.value!!
            setupRecyclerView()
        }

        standingsViewModel.standings.observe(viewLifecycleOwner, standingsObserver)
    }

    private fun setupRecyclerView() = binding.myRecyclerView.apply {
        standingsAdapter = StandingsAdapter(teams)
        adapter = standingsAdapter
        layoutManager = LinearLayoutManager(context)
    }
}