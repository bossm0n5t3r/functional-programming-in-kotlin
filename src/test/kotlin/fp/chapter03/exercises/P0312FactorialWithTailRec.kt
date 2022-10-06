package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0312FactorialWithTailRec {
    private tailrec fun factorialFP(n: Int, acc: Int = 1): Int = when (n) {
        0, 1 -> acc
        else -> factorialFP(n - 1, n * acc)
    }

    @Test
    fun run() {
        assertEquals(factorialFP(5), 120)
    }
}
