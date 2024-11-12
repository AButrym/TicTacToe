package com.example.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                val model by remember { mutableStateOf(Model.random) }

                fun onClick(row: Int, col: Int) {
                    model = model.update(row, col)
                }

                Grid(model)
            }
        }
    }
}

@Composable
fun Grid(model: Model, onClick: (Int, Int) -> Unit) {
    Column {
        repeat(DIM) { row ->
            Row {
                repeat(DIM) { col ->
                    Cell(model[row, col], )
                }
            }

        }
    }
}

@Preview
@Composable
fun GridPreview() {
    Grid(Model.random)
}

@Composable
fun Cell(cell: CellState,
         onClick: () -> Unit
) {
    TextButton(onClick = onClick,
        modifier = Modifier
            .size(80.dp)
            .background(Color.LightGray)
            .padding(2.dp),
        contentPadding = PaddingValues(0.dp)
        ) {
        Text(
            cell.toText(),
            fontSize = 50.sp
        )
    }
}

fun CellState.toText() = when (this) {
    CellState.CROSS -> "❌"
    CellState.NOUGHT -> "⭕"
    CellState.EMPTY -> ""
}

@Preview
@Composable
fun CellPreviewX() {
    Cell(CellState.CROSS)
}

@Preview
@Composable
fun CellPreviewO() {
    Cell(CellState.NOUGHT)
}