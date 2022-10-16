package com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository

import com.example.android.politicalpreparedness.models.Representative

interface PoliticalPreparednessRepository {

    suspend fun getRepresentatives(address: String): List<Representative>?
}