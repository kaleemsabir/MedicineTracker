package com.example.medicinetrackerapp.data

import com.example.medicinetrackerapp.model.Problems
import retrofit2.http.GET
import retrofit2.Response


interface AuthApiService {
    @GET("d1d81d4a-f596-4690-8ad1-fc9be3af514c")
    suspend fun login(): Response<LoginResponse>
}

interface MedicineApiService {
    @GET("5bbc854a-7fa5-4279-ad50-0cd1aa6f6d16")
    suspend fun getMedicineList(): Problems
}

data class LoginResponse(
    val status: String,
    val message: String,
    val data: UserData?
)

data class UserData(
    val userId: Int,
    val token: String
)