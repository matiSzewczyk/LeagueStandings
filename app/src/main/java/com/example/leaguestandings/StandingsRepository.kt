package com.example.leaguestandings

class StandingsRepository(
    private val standingsApi: StandingsApi
){

    suspend fun getStandings(league: Int, season: Int) = standingsApi.getStandings(league, season)
}