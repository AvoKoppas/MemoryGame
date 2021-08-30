package com.testdevlab.numbertapper.ui

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import com.testdevlab.numbertapper.App
import com.testdevlab.numbertapper.common.*
import com.testdevlab.numbertapper.repository.GameRepository
import com.testdevlab.numbertapper.repository.models.GamePiece
import com.testdevlab.numbertapper.repository.models.HighScoreModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject

class GameViewModel : ViewModel() {

    @Inject
    lateinit var repository: GameRepository

    private var timer = object : CountDownTimer(MAX_GAME_TIME, 10) {
        override fun onTick(elapsedTime: Long) {
            val date = Date(MAX_GAME_TIME - elapsedTime)
            _gameTimer.tryEmit(date.time.toTimeString())
        }

        override fun onFinish() {
            TODO("Not yet implemented")
        }

    }
    private val _highScores = MutableSharedFlow<List<HighScoreModel>>(replay = 1)
    private val _gamePieces = MutableSharedFlow<List<GamePiece>>(replay = 1)
    private val _nextButton = MutableSharedFlow<Int>(replay = 1)
    private val _gameTimer = MutableSharedFlow<String>(replay = 1)
    private val _onGameOver = MutableSharedFlow<String>(replay = 1)

    val highScores: SharedFlow<List<HighScoreModel>> = _highScores
    val gamePieces: SharedFlow<List<GamePiece>> = _gamePieces
    val nextButton: SharedFlow<Int> = _nextButton
    val gameTimer: SharedFlow<String> = _gameTimer
    val onGameOver: SharedFlow<String> = _onGameOver

    init {
        App.component.inject(this)
        launchIO {
            repository.highScores.collect { notes ->
                _highScores.tryEmit(notes)
            }
        }
    }

    fun addHighScore(date: String, score: String) = launchIO {
        val id = _highScores.replayCache.lastOrNull()?.maxOfOrNull { it.id }?.plus(1) ?: 0
        repository.insertHighScore(HighScoreModel(id, date, score))
    }

    fun startGame() {
        _onGameOver.resetReplayCache()

        val pieces = mutableListOf<GamePiece>()
        (1..PIECE_COUNT).forEach { number ->
            pieces.add(GamePiece(number))
        }
        pieces.shuffle()
        pieces.shuffle()

        _nextButton.tryEmit(1)
        _gamePieces.tryEmit(pieces)
        timer.start()
    }

    fun openPiece(value: Int) {
        val numbers = _gamePieces.replayCache[0].map { it.copy() }
        val selectedNumber = numbers.first { it.value == value }

        if (selectedNumber.isTapped) return
        selectedNumber.isTapped = true

        if (selectedNumber.value == nextButton.replayCache[0]) {
            selectedNumber.isFound = true
        } else {
            onGameOver(false)
        }
        if (numbers.find { !it.isFound } == null) {
            onGameOver(true)
        } else if (selectedNumber.isFound) {
            _nextButton.tryEmit(selectedNumber.value.plus(1))
        }
        _gamePieces.tryEmit(numbers)
    }

    private fun onGameOver(isGameWon: Boolean) {
        timer.cancel()

        val score = _gameTimer.replayCache[0]
        _onGameOver.tryEmit(_gameTimer.replayCache[0])

        if (isGameWon) {
            addHighScore(Date().time.toDateString(), score)
        }
    }


}