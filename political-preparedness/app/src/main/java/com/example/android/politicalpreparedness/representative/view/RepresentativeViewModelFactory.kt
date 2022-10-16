package com.example.android.politicalpreparedness.representative.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository.PoliticalPreparednessRepository
import java.lang.IllegalArgumentException

class RepresentativeViewModelFactory(private val repository: PoliticalPreparednessRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RepresentativeViewModel::class.java)){
            return RepresentativeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}
