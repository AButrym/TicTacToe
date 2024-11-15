package com.example.tictactoe

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun `isWin is false for empty board`() {
        val model = Model.empty
        assertEquals(GameState.IN_PROCESS, model.gameState)
    }

    @Test
    fun `update empty board should add cross`() {
        val model = Model.empty
        val expected = Model.of("X__|___|___")

        val actual = model.update(0, 0)

        assertEquals(expected, actual)
    }

    @Test
    fun `update board with a single cross should add nought`() {
        val model = Model.of("X__|___|___")
        val expected = Model.of("X__|_O_|___")

        val actual = model.update(1, 1)

        assertEquals(expected, actual)
    }
}