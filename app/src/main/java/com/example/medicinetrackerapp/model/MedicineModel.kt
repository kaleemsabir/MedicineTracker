package com.example.medicinetrackerapp.model


data class Problems(
    val problems: List<Problem>
)

data class Problem(
    val Diabetes: List<Diabetes>?,
    val Asthma: List<Map<String, Any>>?
)

data class Diabetes(
    val medications: List<Medication>,
    val labs: List<Map<String, String>>
)

data class Medication(
    val medicationsClasses: List<MedicationsClass>
)

data class MedicationsClass(
    val className: List<ClassName>,
    val className2: List<ClassName>
)

data class ClassName(
    val associatedDrug: List<Drug>,
    val associatedDrug2: List<Drug>
)

data class Drug(
    val name: String,
    val dose: String?,
    val strength: String
)

