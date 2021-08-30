package com.testdevlab.memorygame.injection

import android.content.Context
import androidx.room.Room
import com.testdevlab.memorygame.common.HIGH_SCORE_DATABASE
import com.testdevlab.memorygame.repository.GameRepository
import com.testdevlab.memorygame.repository.cache.GameDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InjectionModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideNoteDataBase() = Room
        .databaseBuilder(context, GameDatabase::class.java, HIGH_SCORE_DATABASE)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideNoteRepository(database: GameDatabase) = GameRepository(database.gameDao())
}