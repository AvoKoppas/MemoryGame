package com.testdevlab.memorygame.repository.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testdevlab.memorygame.repository.models.HighScoreModel

@Database(entities = [HighScoreModel::class], version = 1, exportSchema = false)
abstract class GameDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao

}