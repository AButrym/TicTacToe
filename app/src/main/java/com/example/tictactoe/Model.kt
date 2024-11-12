package com.example.tictactoe

const val DIM = 3

data class Model(val cells: List<CellState>) {
    companion object
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

fun main() {
    val model = Model(List(DIM * DIM) { CellState.EMPTY })
    val model1 = Model.random
    model.print()
    model1.print()
}