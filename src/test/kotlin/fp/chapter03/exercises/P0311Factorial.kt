package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0311Factorial {
    private fun factorialFP(n: Int): Int = factorialFP(n, 1)

    private tailrec fun factorialFP(n: Int, acc: Int): Int = when (n) {
        0, 1 -> acc
        else -> factorialFP(n - 1, n * acc)
    }

    @Test
    fun run() {
        assertEquals(factorialFP(5), 120)
    }
}
