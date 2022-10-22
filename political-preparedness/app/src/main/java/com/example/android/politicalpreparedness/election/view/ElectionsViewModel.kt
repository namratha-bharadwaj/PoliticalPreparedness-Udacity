package com.example.android.politicalpreparedness.election.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.politicalpreparedness.election.ElectionRepository
import com.example.android.politicalpreparedness.models.Election
import com.example.android.politicalpreparedness.network.api.CivicsApiService
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepository
import kotlinx.coroutines.launch

class ElectionsViewModel(private val api: CivicsApiService, private val repository: ElectionRepository): ViewModel() {

    //Done: Create live data val for upcoming elections
    private var _upcomingElectionsList = MutableLiveData<List<Election>>()
    val upcomingElectionsList: LiveData<List<Election>>
        get() = _upcomingElectionsList

    //Done: Create live data val for saved elections
    private var _savedElectionsList = MutableLiveData<List<Election>>()
    val savedElectionsList: LiveData<List<Election>>
        get() = _savedElectionsList

    fun getUpcomingElections() {
        viewModelScope.launch {
            val response = api.getElectionListAsync()
            try {
                var result = response.await()
                _upcomingElectionsList.value = result.elections
            } catch (e: Exception) {
                Log.e("network error", e.localizedMessage)
            }
        }
    }

    fun getSavedElections() {
        viewModelScope.launch {
            _savedElectionsList.value = repository.getElections()
        }
    }

}