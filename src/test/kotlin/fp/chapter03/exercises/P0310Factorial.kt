package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0310Factorial {
    private fun factorial(memo: IntArray, n: Int): Int = when (n) {
        0, 1 -> {
            memo[0] = 1
            memo[0]
        }
        else -> {
            println("Factorial Memoization($n)")
            memo[n] = n * factorial(memo, n - 1)
            memo[n]
        }
    }

    @Test
    fun run() {
        val memo = IntArray(100) { -1 }
        assertEquals(factorial(memo, 5), 120)
    }
}
