package com.example.android.politicalpreparedness.election

import com.example.android.politicalpreparedness.database.ElectionDao
import com.example.android.politicalpreparedness.models.Election

class ElectionRepository(private val electionDao: ElectionDao) : ElectionDataSource {

    //Done: Add get elections
    override suspend fun getElections(): List<Election> = electionDao.getElections()

    //Done: Add save election
    override suspend fun saveElection(election: Election) {
        electionDao.insertElection(election)
    }

    //Done: Add get election by id
    override suspend fun getElection(id: Int): Election? = electionDao.getElectionById(id)

    //Done: Add delete election by id
    override suspend fun deleteById(id: Int) {
        electionDao.deleteById(id)
    }
}