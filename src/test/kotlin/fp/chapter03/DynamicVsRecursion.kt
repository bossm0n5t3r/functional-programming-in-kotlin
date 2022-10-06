package fp.chapter03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DynamicVsRecursion {
    private fun fiboDynamic(n: Int, fibo: IntArray): Int {
        fibo[0] = 0
        fibo[1] = 1

        for (i in 2..n) {
            fibo[i] = fibo[i - 1] + fibo[i - 2]
        }

        return fibo[n]
    }

    private fun fiboRecursion(n: Int): Int = when (n) {
        0 -> 0
        1 -> 1
        else -> fiboRecursion(n - 2) + fiboRecursion(n - 1)
    }

    @Test
    fun run() {
        assertEquals(fiboDynamic(10, IntArray(100)), 55)
        assertEquals(fiboRecursion(10), 55)
        assertEquals(fiboDynamic(10, IntArray(100)), fiboRecursion(10))
    }
}
