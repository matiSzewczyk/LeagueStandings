package com.example.leaguestandings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StandingsViewModel : ViewModel() {

    var standings = MutableLiveData<List<Standing>>()
    var leagueId = 39
    var season = 2021

    suspend fun getStandings() {
        val response = StandingsRepository(RetrofitInstance.api).getStandings(leagueId, season)

        standings.postValue(mutableListOf(response.body()!!.response[0].league.standings[0]).flatten())
//        println(standings.value)
    }
}