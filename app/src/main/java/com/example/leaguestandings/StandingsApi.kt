package com.example.leaguestandings

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface StandingsApi {

    @Headers(
        "x-rapidapi-host: api-football-v1.p.rapidapi.com",
        "x-rapidapi-key: a6f9374385msh158f578f13d690cp1b0af7jsnf8e0ff676775"
    )
    @GET("standings")
    suspend fun getStandings(
        @Query("league") league: Int,
        @Query("season") season: Int
    ): Response<Standings>
}