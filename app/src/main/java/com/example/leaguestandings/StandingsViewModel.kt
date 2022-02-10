package com.example.leaguestandings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StandingsViewModel : ViewModel() {

    var standings = MutableLiveData<List<Standing>>()

    suspend fun getStandings(league: Int, season: Int) {
        val response = StandingsRepository(RetrofitInstance.api).getStandings(league, season)

        standings.postValue(mutableListOf(response.body()!!.response[0].league.standings[0]).flatten())
//        println(standings.value)
    }
}