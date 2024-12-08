package com.example.medicinetrackerapp.data



import javax.inject.Inject

class AuthRepository @Inject constructor(private val authApiService: AuthApiService) {

    suspend fun login(): Result<UserData> {
        return try {
            val response = authApiService.login()
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.status == "success" && body.data != null) {
                    Result.success(body.data)
                } else {
                    Result.failure(Exception("Login failed: ${body?.message ?: "Unknown error"}"))
                }
            } else {
                Result.failure(Exception("API Error: ${response.errorBody()?.string()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}