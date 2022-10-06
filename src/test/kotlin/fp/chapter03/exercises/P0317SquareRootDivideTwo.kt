package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.math.sqrt
import kotlin.test.assertEquals

class P0317SquareRootDivideTwo {
    private fun squareRoot(x: Double): Double = when {
        x < 1.0 -> x
        else -> divideTwo(sqrt(x))
    }

    private fun divideTwo(x: Double): Double = when {
        x < 1.0 -> x
        else -> squareRoot(x / 2)
    }

    @Test
    fun run() {
        assertEquals(squareRoot(5.0), 0.528685631720282)
    }
}
