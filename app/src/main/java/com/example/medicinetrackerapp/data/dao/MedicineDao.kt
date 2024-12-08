package com.example.medicinetrackerapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicinetrackerapp.data.model.Medicine
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Query("SELECT * FROM Medicine")
    fun getAllMedicines(): Flow<List<Medicine>>

    @Query("SELECT * FROM Medicine WHERE id = :medicineId LIMIT 1")
    suspend fun getMedicineById(medicineId: Int): Medicine?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(medicines: List<Medicine>)
}

