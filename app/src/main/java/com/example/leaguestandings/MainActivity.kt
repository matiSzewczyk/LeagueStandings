package com.example.leaguestandings

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.leaguestandings.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : ActivityMainBinding
    private val standingsViewModel: StandingsViewModel by viewModels()
    private val leagueListViewModel : LeagueListViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_Fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        binding.apply {
            buttonStandings.setOnClickListener {
                val action = NavGraphDirections.actionGlobalLeagueStandingsFragment()
                navController.navigate(action)
            }
            buttonStats.setOnClickListener {
            }
            buttonMatches.setOnClickListener {
            }
        }
    }

    private fun setupSpinner() {
        val spinner = binding.leagueSpinner
        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            leagueListViewModel.leagueList
        )
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        lifecycleScope.launch(Dispatchers.IO) {
            standingsViewModel.setLeague(parent!!.selectedItemPosition)
            standingsViewModel.getStandings()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}