package com.example.leaguestandings

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.leaguestandings.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding : ActivityMainBinding
    private val leagueListViewModel : LeagueListViewModel by viewModels()
    private val standingsViewModel : StandingsViewModel by viewModels()
    private lateinit var adapter: ArrayAdapter<String>
    private var check = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            val spinner = binding.leagueSpinner
            adapter = ArrayAdapter(
                applicationContext,
                R.layout.spinner_item,
                leagueListViewModel.leagueList
            )
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this@MainActivity
        }
        
        binding.apply {
            // TODO: Change fragment here, the fragment should handle the api calls(launch when created)
            buttonStandings.setOnClickListener {
                setFragment(LeagueStandingsFragment())
                Toast.makeText(applicationContext, "changed", Toast.LENGTH_SHORT).show()
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var league = 71
        var test = 5
        if (++check > 1) {
            if (parent!!.selectedItemPosition == 0) {
                league = 38 //Premier League
            }
            if (parent.selectedItemPosition == 1) {
                league = 140 //LaLiga
            }
            if (parent.selectedItemPosition == 2) {
                league = 135 //Serie A
            }
            lifecycleScope.launch(IO) {
                standingsViewModel.getStandings(league, 2021)
//                LeagueStandingsFragment().standingsAdapter.notifyDataSetChanged()
                println("----/n---/n---")
//                println(standingsViewModel.standings.value!!)
//                println(LeagueStandingsFragment().teams)
                Toast.makeText(applicationContext, "${parent.selectedItemPosition}", Toast.LENGTH_SHORT).show()
                test = 6
                println("\ntest 1 = $test")
            }
            LeagueStandingsFragment().test()
            println("\ntest 2 = $test")
//            adapter.notifyDataSetChanged()
        }

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}