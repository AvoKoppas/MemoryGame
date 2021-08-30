package com.testdevlab.memorygame.repository.models

data class GamePiece(
    val value: Int,
    var isTapped: Boolean = false,
    var isFound: Boolean = false
) {
    val valueString = value.toString()
}