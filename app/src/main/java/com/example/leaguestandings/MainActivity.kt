package com.example.leaguestandings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leaguestandings.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            // TODO: Change fragment here, the fragment should handle the api calls(launch when created)
            buttonStandings.setOnClickListener {
                setFragment(LeagueStandingsFragment())
//                Toast.makeText(applicationContext, "changed", Toast.LENGTH_SHORT).show()
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
}