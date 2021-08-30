package com.testdevlab.memorygame.repository.cache

import androidx.room.*
import com.testdevlab.memorygame.repository.models.HighScoreModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM high_score_table")
    fun getHighScores(): Flow<List<HighScoreModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHighScore(noteModel: HighScoreModel)

}
