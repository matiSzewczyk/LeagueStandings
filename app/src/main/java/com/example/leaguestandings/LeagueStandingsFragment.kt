package com.example.leaguestandings

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguestandings.databinding.FragmentLeagueStandingsBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class LeagueStandingsFragment : Fragment(R.layout.fragment_league_standings),
    AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentLeagueStandingsBinding

    private lateinit var standingsAdapter: StandingsAdapter
    private val standingsViewModel : StandingsViewModel by viewModels()
    private val leagueListViewModel : LeagueListViewModel by viewModels()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLeagueStandingsBinding.bind(view)

        setupRecyclerView()

        val spinner = binding.leagueSpinner
        adapter = ArrayAdapter(
            requireContext(),
            R.layout.spinner_item,
            leagueListViewModel.leagueList
        )
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        val standingsObserver = Observer<List<Standing>> {
            standingsAdapter.setListData(standingsViewModel.standings.value!!)
            standingsAdapter.notifyDataSetChanged()
        }

        standingsViewModel.standings.observe(viewLifecycleOwner, standingsObserver)
    }

    private fun setupRecyclerView() = binding.myRecyclerView.apply {
        standingsAdapter = StandingsAdapter()
        adapter = standingsAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent!!.selectedItemPosition == 0) {
            lifecycleScope.launch(IO) {
                standingsViewModel.leagueId = 39 // Premier League
                standingsViewModel.getStandings()
            }
        }
        if (parent.selectedItemPosition == 1) {
            lifecycleScope.launch(IO) {
                standingsViewModel.leagueId = 140 // LaLiga
                standingsViewModel.getStandings()
            }
        }
        if (parent.selectedItemPosition == 2) {
            lifecycleScope.launch(IO) {
                standingsViewModel.leagueId = 135 // Serie A
                standingsViewModel.getStandings()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}