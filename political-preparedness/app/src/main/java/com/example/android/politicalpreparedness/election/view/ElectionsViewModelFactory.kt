package com.example.android.politicalpreparedness.election.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepository
import java.lang.IllegalArgumentException

class ElectionsViewModelFactory(private val repository: PoliticalPreparednessRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ElectionsViewModel::class.java)){
            return ElectionsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}