class BallGame {

    var arc1pos = -1
    var arc2pos = -1
    var arc3pos = -1

    val arc1length = 8
    val arc2length = 10
    val arc3length = 12

    enum class Direction {
        LEFT, RIGHT, STOP
    }

    var dir1 = Direction.STOP
    var dir2 = Direction.STOP
    var dir3 = Direction.STOP

    fun step(state: BallState): BallState {
        TODO()
    }
}

data class BallState(
    val arc1pos: Int,
    val arc2pos: Int,
    val arc3pos: Int,
    val dir1: Direction,
    val dir2: Direction,
    val dir3: Direction
    )