package com.dewerro.watermyplants.di

import android.app.Application
import androidx.room.Room
import com.dewerro.watermyplants.data.PlantRepositoryImpl
import com.dewerro.watermyplants.data.data_source.PlantDatabase
import com.dewerro.watermyplants.domain.repository.PlantRepository
import com.dewerro.watermyplants.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePlantDatabase(app: Application): PlantDatabase {
        return Room.databaseBuilder(
            app,
            PlantDatabase::class.java,
            PlantDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePlantRepository(db: PlantDatabase): PlantRepository {
        return PlantRepositoryImpl(db.plantDao)
    }

    @Provides
    @Singleton
    fun providePlantUseCase(repository: PlantRepository): PlantUseCases {
        return PlantUseCases(
            deletePlantUseCase = DeletePlantUseCase(repository),
            getPlantUseCase = GetPlantUseCase(repository),
            getAllPlantsUseCase = GetAllPlantsUseCase(repository),
            insertPlantUseCase = InsertPlantUseCase(repository)
        )
    }
}