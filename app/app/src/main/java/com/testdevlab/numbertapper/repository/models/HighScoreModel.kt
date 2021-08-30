package com.testdevlab.numbertapper.repository.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testdevlab.numbertapper.common.HIGH_SCORE_TABLE

@Entity(tableName = HIGH_SCORE_TABLE)
data class HighScoreModel(
    @PrimaryKey val id: Int,
    val date: String,
    val score: String
)
