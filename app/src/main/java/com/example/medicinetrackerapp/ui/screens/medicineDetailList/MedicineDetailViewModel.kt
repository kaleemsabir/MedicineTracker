package com.example.medicinetrackerapp.ui.screens.medicineDetailList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicinetrackerapp.data.MedicineRepository
import com.example.medicinetrackerapp.data.model.Medicine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineDetailViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {

    private val _medicineDetail = MutableStateFlow<Medicine?>(null)
    val medicineDetail: StateFlow<Medicine?> = _medicineDetail

    fun fetchMedicineDetail(medicineId: Int) {
        viewModelScope.launch {
            val medicine = repository.getMedicineById(medicineId)
            _medicineDetail.value = medicine
        }
    }
}
