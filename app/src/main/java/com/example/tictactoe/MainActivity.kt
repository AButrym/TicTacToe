package com.example.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.Display.Mode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.TicTacToeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main()
        }
    }
}

@Composable
fun Main() {
    TicTacToeTheme {
        var model by remember { mutableStateOf(Model.empty) }
        val isEnd by remember { derivedStateOf { model.gameState != GameState.IN_PROCESS } }

        fun onClick(row: Int, col: Int) {
            model = model.update(row, col)
            Log.i("CellClick", "cell ($row, $col) is clicked")
        }

        Grid(model, isEnd, ::onClick)
    }
}

@Composable
fun Grid(model: Model, isEnd: Boolean = false, onClick: (Int, Int) -> Unit = { _, _ -> }) {
    Column(modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isEnd) {
            WinBanner(model)
        }
        repeat(DIM) { row ->
            Row {
                repeat(DIM) { col ->
                    Cell(model[row, col]) { onClick(row, col) }
                }
            }
        }
    }
}

@Composable
fun WinBanner(model: Model) {
    val gameState = model.gameState
    val style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
    when (gameState) {
        GameState.CROSS_WIN -> {
            Text("Cross wins!", style = style)
        }
        GameState.NOUGHT_WIN -> {
            Text("Nought wins!", style = style)
        }
        GameState.DRAW -> {
            Text("Draw!", style = style)
        }
        else -> error("Should not get here!")
    }
}

@Preview(showSystemUi = true)
@Composable
fun GridPreview() {
    Main()
}

@Composable
fun Cell(cell: CellState,
         onClick: () -> Unit = {}
) {
    TextButton(onClick = onClick,
        modifier = Modifier
            .size(120.dp)
            .padding(2.dp)
            .background(Color.LightGray),
        contentPadding = PaddingValues(0.dp)
        ) {
        Text(
            cell.toText(),
            fontSize = 88.sp
        )
    }
}

fun CellState.toText() = when (this) {
    CellState.CROSS -> "❌"
    CellState.NOUGHT -> "⭕"
    CellState.EMPTY -> ""
}

//@Preview
@Composable
fun CellPreviewX() {
    Cell(CellState.CROSS)
}

//@Preview
@Composable
fun CellPreviewO() {
    Cell(CellState.NOUGHT)
}