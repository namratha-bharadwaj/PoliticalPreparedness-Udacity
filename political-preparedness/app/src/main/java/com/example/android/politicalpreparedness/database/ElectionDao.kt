package com.example.android.politicalpreparedness.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.politicalpreparedness.models.Election

@Dao
interface ElectionDao {

    //Done: Add insert query
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertElection(election: Election)

    //Done: Add select all election query
    @Query("SELECT * FROM election_table")
    suspend fun getElections(): List<Election>

    //Done: Add select single election query
    @Query("SELECT * from election_table where id = :id")
    suspend fun getElectionById(id: Int): Election?

    //Done: Add delete query
    @Query("DELETE FROM election_table where id = :id")
    suspend fun deleteById(id: Int)

    //Done: Add clear query
    @Query("DELETE FROM election_table")
    suspend fun deleteElections()
}