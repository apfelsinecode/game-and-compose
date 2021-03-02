import kotlin.random.Random

class BallGame {

    var arc1pos = -1
    var arc2pos = -1
    var arc3pos = -1

    val arc1length = 8
    val arc2length = 10
    val arc3length = 12

}

enum class Direction {
    LEFT, RIGHT, STOP
}

data class BallState(

    var handPos: Int = 2,
) {
    // lateinit var arc1: ArcState
    // lateinit var arc2: ArcState
    // lateinit var arc3: ArcState
    lateinit var arcs: Array<ArcState>
    val arc3length: Int = 8
    val arc2length: Int = 10
    val arc1length: Int = 12

    constructor() : this(2) {
        val arc1 = ArcState(
            position = Random.nextInt(from = (0.3 * arc1length).toInt(), until = (0.7 * arc1length).toInt()),
            direction = if (Random.nextBoolean()) Direction.LEFT else Direction.RIGHT
        )
        val arc2 = ArcState(
            position = Random.nextInt(from = (0.3 * arc2length).toInt(), until = (0.7 * arc2length).toInt()),
            direction = if (Random.nextBoolean()) Direction.LEFT else Direction.RIGHT
        )
        val arc3 = ArcState(
            position = Random.nextInt(from = (0.3 * arc3length).toInt(), until = (0.7 * arc3length).toInt()),
            direction = if (Random.nextBoolean()) Direction.LEFT else Direction.RIGHT
        )
        arcs = arrayOf(arc1, arc2, arc3)
        println(this)
    }



    fun leftClick(): BallState {
        handPos = when (handPos) {
            1, 2 -> {
                arcs[0] = arcs[0].copy(reflected = true)
                1
            }
            3 -> {
                arcs[1] = arcs[1].copy(reflected = true)
                2
            }
            else -> {
                arcs[2] = arcs[2].copy(reflected = true)
                3
            }
        }
        return this
    }

    fun rightClick() {
        handPos = when (handPos) {
            1 -> {
                arcs[1] = arcs[1].copy(reflected = true)
                2
            }
            2, 3 -> {
                arcs[2] = arcs[2].copy(reflected = true)
                3
            }
            else -> {
                arcs[0] = arcs[0].copy(reflected = true)
                1
            }
        }
    }


    fun step(): BallState {
        for ((index, arc) in arcs.withIndex())
        {
            when (arc.direction) {
                Direction.LEFT ->
                    if (arc.reflected)
                        arcs[index] = arc.copy(direction = Direction.RIGHT)
                    else
                        arcs[index] = arc.copy(position = arc.position - 1)
                Direction.RIGHT ->
                    if (arc.reflected)
                        arcs[index] = arc.copy(direction = Direction.LEFT)
                    else
                        arcs[index] = arc.copy(position = arc.position + 1)
                else -> {}
            }
            if (arc.reflected) {
                arcs[index] = arc.copy(reflected = false)
            }
        }
        println(this)
        return this
    }

    override fun toString(): String {
        return "BallState(handPos=$handPos, arcs=${arcs.contentToString()}, arc3length=$arc3length, arc2length=$arc2length, arc1length=$arc1length)"
    }


}




data class ArcState(
    val position: Int,
    val direction: Direction,
    val reflected: Boolean = false
)

