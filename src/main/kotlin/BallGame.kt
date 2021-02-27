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
    lateinit var arc1: ArcState
    lateinit var arc2: ArcState
    lateinit var arc3: ArcState
    val arc3length: Int = 8
    val arc2length: Int = 10
    val arc1length: Int = 12

    constructor() : this(2) {
        arc1 = ArcState(
            position = Random.nextInt(from = (0.3 * arc1length).toInt(), until = (0.7 * arc1length).toInt()),
            direction = if (Random.nextBoolean()) Direction.LEFT else Direction.RIGHT
        )
        arc2 = ArcState(
            position = Random.nextInt(from = (0.3 * arc2length).toInt(), until = (0.7 * arc2length).toInt()),
            direction = if (Random.nextBoolean()) Direction.LEFT else Direction.RIGHT
        )
        arc3 = ArcState(
            position = Random.nextInt(from = (0.3 * arc3length).toInt(), until = (0.7 * arc3length).toInt()),
            direction = if (Random.nextBoolean()) Direction.LEFT else Direction.RIGHT
        )
        println(this)
    }



    fun leftClick() {
        handPos = when (handPos) {
            1, 2 -> {
                arc1.reflected = true
                1
            }
            3 -> {
                arc2.reflected = true
                2
            }
            else -> {
                arc3.reflected = true
                3
            }
        }
    }

    fun rightClick() {
        handPos = when (handPos) {
            1 -> {
                arc2.reflected = true
                2
            }
            2, 3 -> {
                arc3.reflected = true
                3
            }
            else -> {
                arc1.reflected = true
                1
            }
        }
    }


    fun step(): BallState {
        for (arc in arrayOf(arc1, arc2, arc3))
        {
            when (arc.direction) {
                Direction.LEFT ->
                    if (arc.reflected)
                        arc.direction = Direction.RIGHT
                    else
                        arc.position--
                Direction.RIGHT ->
                    if (arc.reflected)
                        arc.direction = Direction.LEFT
                    else
                        arc.position++
                else -> {}
            }
            arc.reflected = false
        }
        println(this)
        return this
    }

    override fun toString(): String {
        return "BallState(arc1=$arc1, arc2=$arc2, arc3=$arc3, handPos=$handPos, arc3length=$arc3length, arc2length=$arc2length, arc1length=$arc1length)"
    }
}




data class ArcState(
    var position: Int,
    var direction: Direction,
    var reflected: Boolean = false
)