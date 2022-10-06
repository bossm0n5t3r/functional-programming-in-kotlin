package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0302Power {
    private fun power(x: Double, n: Int): Double = when (n) {
        0 -> 1.0
        else -> x * power(x, n - 1)
    }

    @Test
    fun run() {
        assertEquals(power(2.0, 10), 1024.0)
    }
}
