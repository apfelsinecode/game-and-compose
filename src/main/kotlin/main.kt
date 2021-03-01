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

    var handPos by remember { mutableStateOf( 2)}
    var arc1 by remember { mutableStateOf(ballState.arc1) }
    var arc2 by remember { mutableStateOf(ballState.arc2) }
    var arc3 by remember { mutableStateOf(ballState.arc3) }


    val leftClick: () -> Unit = {
        ballState = ballState.leftClick()
        handPos = ballState.handPos
    }
    val rightClick = {
        ballState.rightClick()
        ballState = ballState.copy()
        handPos = ballState.handPos
    }

    val step = {
        ballState.step()
        ballState = ballState.copy()
        arc1 = ballState.arc1.copy()
        arc2 = ballState.arc2.copy()
        arc3 = ballState.arc3.copy()
    }

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

            ball(ballState, arc1, arc2, arc3, handPos = handPos, leftClick = leftClick, rightClick = rightClick, step = step)


        }


    }
}



@Composable
fun hand(active: Boolean) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .width(40.dp)
            .padding(10.dp)
            .background(color = if (active) Color.Black else Color.Gray)
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
fun ball(ballState: BallState, arc1: ArcState, arc2: ArcState, arc3: ArcState,
         handPos: Int,  leftClick: () -> Unit, rightClick: () -> Unit, step: () -> Unit) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
    ){

        Row {
            hand(active = handPos == 1)
            arc(size = ballState.arc1length, ballPos = arc1.position)
            hand(active = handPos == 1)
        }
        Row {
            hand(active = handPos == 2)
            arc(size = ballState.arc2length, ballPos = arc2.position)
            hand(active = handPos == 2)
        }
        Row {
            hand(active = handPos == 3)
            arc(size = ballState.arc3length, ballPos = arc3.position)
            hand(active = handPos == 3)
        }


        Row(modifier = Modifier
            .fillMaxWidth()) {
            Button(onClick = leftClick) {
                Text("<")
            }
            Button(onClick = rightClick /*; setBallStateToSelf()*/ ){
                Text(">")
            }
            Button(onClick = step) {
                Text("step()")
            }
        }
    }
}

@Composable
fun previewBall() {
    // ball()
}