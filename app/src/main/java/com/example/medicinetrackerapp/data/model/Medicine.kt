package com.example.medicinetrackerapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Medicine(
    @PrimaryKey val id: Int,
    val name: String,
    val dose: String,
    val strength: String
)