package com.example.tictactoe

const val DIM = 3

data class Model(val cells: List<CellState>) {
    companion object {
        val empty = Model.of("___|___|___")
    }
}

val Model.gameState get(): GameState = TODO()

fun Model.update(row: Int, col: Int): Model {
    TODO()
}

enum class GameState {
    CROSS_WIN,
    NOUGHT_WIN,
    DRAW,
    IN_PROCESS
}

enum class CellState {
    CROSS,
    NOUGHT,
    EMPTY
}

fun ix(row: Int, col: Int) = row * DIM + col

operator fun Model.get(row: Int, col: Int) = cells[ix(row, col)]

val CellState.str get() =
    when (this) {
        CellState.CROSS -> "X"
        CellState.NOUGHT -> "O"
        CellState.EMPTY -> "_"
    }

fun Model.print() {
    repeat(3) { row ->
        repeat(3) { col ->
            print(this[row, col].str)
        }
        println()
    }
}

val Model.Companion.random get() =
    Model(List(DIM * DIM) { CellState.entries.random() })

fun Model.Companion.of(str: String): Model {
    val list = mutableListOf<CellState>()
    for (ch in str) {
        when (ch) {
            'X' -> list.add(CellState.CROSS)
            'O', '0' -> list.add(CellState.NOUGHT)
            '_', ' ' -> list.add(CellState.EMPTY)
        }
    }
    return Model(list)
}

fun main() {
    val model = Model.of("___|___|___")
    val model1 = Model.of("X__|OX_|_OX")
    model.print()
    model1.print()
}