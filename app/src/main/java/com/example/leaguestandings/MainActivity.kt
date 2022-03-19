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
import com.example.leaguestandings.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : ActivityMainBinding
    private val standingsViewModel: StandingsViewModel by viewModels()
    private val leagueListViewModel : LeagueListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()

        binding.apply {
            buttonStandings.setOnClickListener {
                setFragment(LeagueStandingsFragment())
            }
            buttonStats.setOnClickListener {
            }
            buttonMatches.setOnClickListener {
            }
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_Fragment, fragment)
            addToBackStack(null)
            commit()
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