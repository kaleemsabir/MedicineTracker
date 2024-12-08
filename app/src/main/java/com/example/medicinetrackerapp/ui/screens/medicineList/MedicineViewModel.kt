package com.example.medicinetrackerapp.ui.screens.medicineList

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
class MedicineViewModel @Inject constructor(
    private val repository: MedicineRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MedicineUIState>(MedicineUIState.Idle)
    val uiState: StateFlow<MedicineUIState> = _uiState

    init {
        fetchMedicines()
    }

    fun fetchMedicines() {
        _uiState.value = MedicineUIState.Loading

        viewModelScope.launch {
            try {
                repository.fetchMedicines()
                val medicines = repository.medicineState.value?.getOrNull().orEmpty()
                _uiState.value = MedicineUIState.Success(medicines)
            } catch (e: Exception) {
                _uiState.value = MedicineUIState.Error(e.message ?: "Unknown error")
            }
        }
    }
}


sealed class MedicineUIState {
    object Loading : MedicineUIState()
    data class Success(val medicines: List<Medicine>) : MedicineUIState()
    data class Error(val message: String) : MedicineUIState()
    object Idle : MedicineUIState()
}
