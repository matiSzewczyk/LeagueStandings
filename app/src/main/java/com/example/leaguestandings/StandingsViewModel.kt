package com.example.leaguestandings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StandingsViewModel : ViewModel() {

    var standings = MutableLiveData<List<Standing>>()
    private var leagueId = 39
    private var season = 2021
    private val listOfIDs = listOf(
        39,     // Premier League
        140,    // LaLiga
        135     // Serie A
    )

    suspend fun getStandings() {
        val response = StandingsRepository(RetrofitInstance.api).getStandings(leagueId, season)
        standings.postValue(mutableListOf(response.body()!!.response[0].league.standings[0]).flatten())
    }

    fun setLeague(id: Int) {
        leagueId = listOfIDs[id]
    }
}