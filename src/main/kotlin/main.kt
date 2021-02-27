import androidx.compose.desktop.Window
import androidx.compose.foundation.Canvas
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

enum class Direction2 {
    LEFT, RIGHT, STOP
}

const val arc1length = 8
const val arc2length = 10
const val arc3length = 12

fun main() = Window {
    var text by remember { mutableStateOf("Hello, World!") }
    var text3 by remember { mutableStateOf("Hello2") }

    var ballState by remember { mutableStateOf(BallState()) }

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

            ball(ballState, setBallStateToSelf = {ballState = ballState.copy()})


        }


    }
}

fun leftClick() {

}

fun rightClick() {
    println("right")
}


@Composable
fun hand(active: Boolean) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(20.dp)
            .padding(10.dp)
            .background(color = if (active) Color.Gray else Color.Black)
    )
}

@Composable
fun ballPlaceholder(active: Boolean) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(50.dp)
            .padding(10.dp)
            // .background(color = if (active) Color.Blue else Color.Gray)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = if (active) Color.Blue else Color.Gray,
                center = center
            )
        }
    }
}

/**
 * An arc, that a ball travels on
 */
@Composable
fun arc(size: Int, ballPos: Int?) {
    Row(
    ) {
        //hand(active = false)
        for (i in 0..size) {
            ballPlaceholder(active = i == ballPos)
        }
        //hand(active = false)
    }
}

@Composable
fun ball(ballState: BallState, setBallStateToSelf: () -> Unit) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
    ){

        Row {
            hand(active = ballState.handPos == 1)
            arc(size = ballState.arc1length, ballPos = ballState.arc1.position)
            hand(active = ballState.handPos == 1)
        }
        Row {
            hand(active = ballState.handPos == 2)
            arc(size = ballState.arc2length, ballPos = ballState.arc2.position)
            hand(active = ballState.handPos == 2)
        }
        Row {
            hand(active = ballState.handPos == 3)
            arc(size = ballState.arc3length, ballPos = ballState.arc3.position)
            hand(active = ballState.handPos == 3)
        }


        Row(modifier = Modifier
            .fillMaxWidth()) {
            Button(onClick = { ballState.leftClick(); setBallStateToSelf() }) {
                Text("<")
            }
            Button(onClick = { ballState.rightClick(); setBallStateToSelf() }) {
                Text(">")
            }
            Button(onClick = { ballState.step(); setBallStateToSelf() }) {
                Text("step()")
            }
        }
    }
}

@Composable
fun previewBall() {
    // ball()
}