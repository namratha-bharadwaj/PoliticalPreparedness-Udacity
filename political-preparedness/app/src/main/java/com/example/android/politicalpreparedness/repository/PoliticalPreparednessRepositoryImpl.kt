package com.example.android.politicalpreparedness.repository.PoliticalPreparednessRepository

import android.util.Log
import com.example.android.politicalpreparedness.network.api.CivicsApiService
import com.example.android.politicalpreparedness.models.Representative
import java.lang.Exception

class PoliticalPreparednessRepositoryImpl(private val civicsServicesApi: CivicsApiService): PoliticalPreparednessRepository {

    override suspend fun getRepresentatives(address: String): List<Representative>? {
        return try {
            val (offices, officials) = civicsServicesApi.getRepresentativesAsync(address).await()
            offices.flatMap { office ->
                office.getRepresentatives(officials)
            }
        } catch (e: Exception) {
            Log.e("Representative", e.localizedMessage)
            null
        }
    }
}