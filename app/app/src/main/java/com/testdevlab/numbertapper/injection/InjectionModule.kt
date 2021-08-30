package com.testdevlab.numbertapper.injection

import android.content.Context
import androidx.room.Room
import com.testdevlab.numbertapper.common.HIGH_SCORE_DATABASE
import com.testdevlab.numbertapper.repository.GameRepository
import com.testdevlab.numbertapper.repository.cache.GameDatabase
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