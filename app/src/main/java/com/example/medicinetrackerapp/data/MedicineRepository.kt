package com.example.medicinetrackerapp.data

import com.example.medicinetrackerapp.data.dao.MedicineDao
import com.example.medicinetrackerapp.data.model.Medicine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MedicineRepository @Inject constructor(
    private val apiService: MedicineApiService,
    private val medicineDao: MedicineDao
) {

    private val _medicineState = MutableStateFlow<Result<List<Medicine>>?>(null)
    val medicineState: StateFlow<Result<List<Medicine>>?> = _medicineState

    suspend fun fetchMedicines() {
        try {
            // Fetch the medicine list from the API
            val problems = apiService.getMedicineList()

            // Transform the nested structure into a flat list of medicines
            val medicines = problems.problems.flatMap { problem ->
                problem.Diabetes?.flatMap { diabetes ->
                    diabetes.medications.flatMap { medication ->
                        medication.medicationsClasses.flatMap { medicationClass ->
                            medicationClass.className.flatMap { className ->
                                className.associatedDrug.map { drug ->
                                    Medicine(
                                        id = drug.name.hashCode(),
                                        name = drug.name,
                                        dose = drug.dose.orEmpty(),
                                        strength = drug.strength
                                    )
                                }
                            } + medicationClass.className2.flatMap { className ->
                                className.associatedDrug.map { drug ->
                                    Medicine(
                                        id = drug.name.hashCode(),
                                        name = drug.name,
                                        dose = drug.dose.orEmpty(),
                                        strength = drug.strength
                                    )
                                }
                            }
                        }
                    }
                }.orEmpty()
            }.orEmpty()

            // Insert the medicines into the database
            medicineDao.insertAll(medicines)

            // Emit the successful result
            _medicineState.value = Result.success(medicines)
        } catch (e: Exception) {
            // Emit the failure result
            _medicineState.value = Result.failure(e)
        }
    }
    suspend fun getMedicineById(medicineId: Int): Medicine? {
        return medicineDao.getMedicineById(medicineId)
    }
}