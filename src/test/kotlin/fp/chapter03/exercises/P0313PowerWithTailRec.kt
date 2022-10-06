package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0313PowerWithTailRec {
    private tailrec fun power(x: Double, n: Int, acc: Double = 1.0): Double = when (n) {
        0 -> acc
        else -> power(x, n - 1, acc * x)
    }

    @Test
    fun run() {
        assertEquals(power(2.0, 10), 1024.0)
    }
}
