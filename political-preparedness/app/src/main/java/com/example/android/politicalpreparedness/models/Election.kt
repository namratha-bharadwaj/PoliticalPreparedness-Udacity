package com.example.android.politicalpreparedness.models

import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.*
import java.util.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "election_table")
data class Election(
        @PrimaryKey val id: Int,
        @ColumnInfo(name = "name")val name: String,
        @ColumnInfo(name = "electionDay")val electionDay: Date,
        @Embedded(prefix = "division_") @Json(name="ocdDivisionId") val division: Division
) : Parcelable