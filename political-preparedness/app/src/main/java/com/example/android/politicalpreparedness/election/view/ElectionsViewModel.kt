package com.example.android.politicalpreparedness.election.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.politicalpreparedness.models.Election
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepository

class ElectionsViewModel(private val repository: PoliticalPreparednessRepository): ViewModel() {

    //Done: Create live data val for upcoming elections
    private var _upcomingElectionsList = MutableLiveData<List<Election>>()
    val upcomingElectionsList: LiveData<List<Election>>
        get() = _upcomingElectionsList

    //Done: Create live data val for saved elections
    private var _savedElectionsList = MutableLiveData<List<Election>>()
    val savedElectionsList: LiveData<List<Election>>
        get() = _savedElectionsList

    fun getUpcomingElections() {

    }

    fun getSavedElections() {

    }

    //TODO: Create functions to navigate to saved or upcoming election voter info

}