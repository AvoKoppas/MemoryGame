package com.testdevlab.numbertapper.repository.cache

import androidx.room.*
import com.testdevlab.numbertapper.repository.models.HighScoreModel
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM high_score_table")
    fun getHighScores(): Flow<List<HighScoreModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHighScore(noteModel: HighScoreModel)

}
