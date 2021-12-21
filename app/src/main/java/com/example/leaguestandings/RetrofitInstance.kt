package com.example.leaguestandings

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    val api: StandingsApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api-football-v1.p.rapidapi.com/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}