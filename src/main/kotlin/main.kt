import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun main() = Window {
    var text by remember { mutableStateOf("Hello, World!") }
    var text3 by remember { mutableStateOf("Hello2") }

    MaterialTheme {
        Column {
            /*Row {
                Button(onClick = {
                    text = "Hello, Desktop!"
                }) {
                    Text(text)
                }

                Button(onClick = {
                    text3 = "Hallo"
                }) {
                    Text(text3)
                }


            }*/

            ball()

            Row {
                Button(onClick = { leftClick() }) {
                    Text("<")
                }
                Button(onClick = { rightClick() }) {
                    Text(">")
                }
            }
        }


    }
}

fun leftClick() {
    println("left")
}

fun rightClick() {
    println("right")
}


@Composable
fun hand(active: Boolean) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(50.dp)
            .padding(10.dp)
            .background(color = if (active) Color.Gray else Color.Black)
    )
}

@Composable
fun ballPlaceholder(active: Boolean) {
    Box(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .padding(10.dp)
            .background(color = if (active) Color.Blue else Color.Gray)
    )
}

/**
 * An arc, that a ball travels on
 */
@Composable
fun arc(size: Int, ballPos: Int?) {
    Row(
    ) {
        hand(active = false)
        for (i in 0..size) {
            ballPlaceholder(active = i == ballPos)
        }
        hand(active = false)
    }
}

@Composable
fun ball() {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
    ){

        arc(size = 12, ballPos = 0)
        arc(size = 10, ballPos = null)
        arc(size = 8, ballPos = 3)

        /*Column {

            }


        }
        Column {

        }
        Column {

        }
        Column {

        }
        Column {

        }
        Column {

        }*/
    }
}

@Composable
fun previewBall() {
    ball()
}