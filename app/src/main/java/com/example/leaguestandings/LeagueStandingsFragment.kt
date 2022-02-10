package com.example.leaguestandings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguestandings.databinding.FragmentLeagueStandingsBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val LEAGUE = 39
const val SEASON = 2021

class LeagueStandingsFragment : Fragment(R.layout.fragment_league_standings) {

    private lateinit var binding: FragmentLeagueStandingsBinding

    lateinit var standingsAdapter: StandingsAdapter
    private val standingsViewModel : StandingsViewModel by viewModels()
    lateinit var teams: List<Standing>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLeagueStandingsBinding.bind(view)

        setupRecyclerView()
        lifecycleScope.launch(Main) {
            standingsViewModel.getStandings(LEAGUE, SEASON)
        }

        val standingsObserver = Observer<List<Standing>> { newTeams ->
            teams = newTeams
            standingsAdapter.setListData(standingsViewModel.standings.value!!)
            standingsAdapter.notifyDataSetChanged()
        }

        standingsViewModel.standings.observe(viewLifecycleOwner, standingsObserver)

        binding.button.setOnClickListener {
            lifecycleScope.launch(IO) {
                standingsViewModel.getStandings(135, 2021)
                }
        }
    }

    private fun setupRecyclerView() = binding.myRecyclerView.apply {
        standingsAdapter = StandingsAdapter()
        adapter = standingsAdapter
        layoutManager = LinearLayoutManager(context)
    }

    suspend fun test() {
        withContext(Main) {
            teams = standingsViewModel.standings.value!!
            setupRecyclerView()
        }
    }

    suspend fun test1() {
        withContext(Main) {
            teams = standingsViewModel.standings.value!!
        }
    }
}