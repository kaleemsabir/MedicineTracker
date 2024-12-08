package com.example.medicinetrackerapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.medicinetrackerapp.data.AuthApiService
import com.example.medicinetrackerapp.data.AuthRepository
import com.example.medicinetrackerapp.data.MedicineApiService
import com.example.medicinetrackerapp.data.MedicineDatabase
import com.example.medicinetrackerapp.data.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApiService: AuthApiService): AuthRepository {
        return AuthRepository(authApiService)
    }

    @Provides
    @Singleton
    fun provideAuthApiService(retrofit: Retrofit): AuthApiService {
        return retrofit.create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMedicineApiService(retrofit: Retrofit): MedicineApiService {
        return retrofit.create(MedicineApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: MedicineApiService, db: MedicineDatabase): MedicineRepository {
        return MedicineRepository(apiService, db.medicineDao())
    }
     @Singleton
     @Provides
     fun provideRoomDatabase(application: Application): MedicineDatabase {
         return Room.databaseBuilder(application.applicationContext, MedicineDatabase::class.java, "xplora_db").build()
     }
}
