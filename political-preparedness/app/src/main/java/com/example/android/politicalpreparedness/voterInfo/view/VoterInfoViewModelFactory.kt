package com.example.android.politicalpreparedness.voterInfo.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.politicalpreparedness.database.ElectionDatabase
import com.example.android.politicalpreparedness.election.ElectionRepository
import com.example.android.politicalpreparedness.network.api.CivicsApi
import java.lang.IllegalArgumentException

class VoterInfoViewModelFactory(private val context: Context): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VoterInfoViewModel::class.java)){
            return VoterInfoViewModel(CivicsApi.retrofitService, ElectionRepository(ElectionDatabase.getInstance(context).electionDao)) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}