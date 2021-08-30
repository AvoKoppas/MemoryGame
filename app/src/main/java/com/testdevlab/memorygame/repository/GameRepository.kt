package com.testdevlab.memorygame.repository

import com.testdevlab.memorygame.repository.cache.GameDao
import com.testdevlab.memorygame.repository.models.HighScoreModel

class GameRepository(private val gameDao: GameDao) {

    val highScores = gameDao.getHighScores()

    fun insertHighScore(noteModel: HighScoreModel) = gameDao.insertHighScore(noteModel)
}