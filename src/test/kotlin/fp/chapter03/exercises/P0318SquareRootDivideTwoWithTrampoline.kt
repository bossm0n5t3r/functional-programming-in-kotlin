package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.math.sqrt
import kotlin.test.assertEquals

class P0318SquareRootDivideTwoWithTrampoline {
    sealed class Bounce<T>
    data class Done<T>(val result: T) : Bounce<T>()
    data class More<T>(val thunk: () -> Bounce<T>) : Bounce<T>()

    private tailrec fun <T> trampoline(bounce: Bounce<T>): T = when (bounce) {
        is Done -> bounce.result
        is More -> trampoline(bounce.thunk())
    }

    private fun squareRoot(x: Double): Bounce<Double> = when {
        x < 1.0 -> Done(x)
        else -> More { divideTwo(sqrt(x)) }
    }

    private fun divideTwo(x: Double): Bounce<Double> = when {
        x < 1.0 -> Done(x)
        else -> More { squareRoot(x / 2) }
    }

    @Test
    fun run() {
        assertEquals(trampoline(squareRoot(5.0)), 0.528685631720282)
    }
}
