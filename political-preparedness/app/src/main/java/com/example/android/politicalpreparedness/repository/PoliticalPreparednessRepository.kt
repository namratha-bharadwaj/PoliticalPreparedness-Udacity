package com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository

import com.example.android.politicalpreparedness.models.Election
import com.example.android.politicalpreparedness.models.ElectionResponse
import com.example.android.politicalpreparedness.models.Representative

interface PoliticalPreparednessRepository {

    suspend fun getRepresentatives(address: String): List<Representative>?

}