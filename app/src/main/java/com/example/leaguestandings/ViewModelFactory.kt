package com.example.leaguestandings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val standingsRepository: StandingsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StandingsViewModel::class.java)) {
            return StandingsViewModel(standingsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}