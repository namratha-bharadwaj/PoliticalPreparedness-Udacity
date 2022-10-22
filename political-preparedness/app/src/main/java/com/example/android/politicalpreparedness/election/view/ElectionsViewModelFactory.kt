package com.example.android.politicalpreparedness.election.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.database.ElectionDatabase
import com.example.android.politicalpreparedness.election.ElectionRepository
import com.example.android.politicalpreparedness.network.api.CivicsApi
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepository
import java.lang.IllegalArgumentException

class ElectionsViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ElectionsViewModel::class.java)){
            return ElectionsViewModel(CivicsApi.retrofitService, ElectionRepository(ElectionDatabase.getInstance(context).electionDao)) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}