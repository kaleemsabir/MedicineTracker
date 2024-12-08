package com.example.medicinetrackerapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medicinetrackerapp.data.dao.MedicineDao
import com.example.medicinetrackerapp.data.model.Medicine

@Database(entities = [Medicine::class], version = 1)
abstract class MedicineDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}
