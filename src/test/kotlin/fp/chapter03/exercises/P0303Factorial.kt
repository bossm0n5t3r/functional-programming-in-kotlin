package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0303Factorial {
    private fun factorial(n: Int): Int = when (n) {
        0 -> 1
        else -> n * factorial(n - 1)
    }

    @Test
    fun run() {
        assertEquals(factorial(5), 120)
    }
}
