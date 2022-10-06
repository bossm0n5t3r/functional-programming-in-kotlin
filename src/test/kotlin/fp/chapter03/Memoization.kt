package fp.chapter03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Memoization {
    private fun fiboRecursion(n: Int): Int {
        println("fiboRecursion($n)")
        return when (n) {
            0 -> 0
            1 -> 1
            else -> fiboRecursion(n - 2) + fiboRecursion(n - 1)
        }
    }

    @Test
    fun fiboRecursionTest() {
        assertEquals(fiboRecursion(6), 8)
    }

    private fun fiboMemoization(memo: IntArray, n: Int): Int = when {
        n == 0 -> 0
        n == 1 -> 1
        memo[n] != -1 -> memo[n]
        else -> {
            println("fiboMemoization($n)")
            memo[n] = fiboMemoization(memo, n - 2) + fiboMemoization(memo, n - 1)
            memo[n]
        }
    }

    @Test
    fun fiboMemoizationTest() {
        val memo = IntArray(100) { -1 }
        assertEquals(fiboMemoization(memo, 6), 8)
    }

    private fun fiboFP(n: Int): Int = fiboFP(n, 0, 1)

    private tailrec fun fiboFP(n: Int, first: Int, second: Int): Int = when (n) {
        0 -> first
        1 -> second
        else -> fiboFP(n - 1, second, first + second)
    }

    @Test
    fun fiboFPTest() {
        assertEquals(fiboFP(6), 8)
    }
}
