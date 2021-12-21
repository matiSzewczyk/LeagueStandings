package com.example.leaguestandings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StandingsViewModel(
    private val standingsRepository: StandingsRepository
) : ViewModel() {

    var standings = MutableLiveData<List<Standing>>()

    suspend fun getStandings(league: Int, season: Int) {
        val response = standingsRepository.getStandings(league, season)

        standings.value = mutableListOf(response.body()!!.response[0].league.standings[0]).flatten()
    }
}