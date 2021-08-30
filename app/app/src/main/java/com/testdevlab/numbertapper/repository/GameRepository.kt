package com.testdevlab.numbertapper.repository

import com.testdevlab.numbertapper.repository.cache.GameDao
import com.testdevlab.numbertapper.repository.models.HighScoreModel

class GameRepository(private val gameDao: GameDao) {

    val highScores = gameDao.getHighScores()

    fun insertHighScore(noteModel: HighScoreModel) = gameDao.insertHighScore(noteModel)
}