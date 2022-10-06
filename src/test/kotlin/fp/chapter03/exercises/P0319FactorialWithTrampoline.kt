package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.math.BigDecimal

class P0319FactorialWithTrampoline {
    sealed class Bounce<T>
    data class Done<T>(val result: T) : Bounce<T>()
    data class More<T>(val thunk: () -> Bounce<T>) : Bounce<T>()

    private tailrec fun <T> trampoline(bounce: Bounce<T>): T = when (bounce) {
        is Done -> bounce.result
        is More -> trampoline(bounce.thunk())
    }

    private fun factorial(n: BigDecimal, acc: BigDecimal = BigDecimal(1)): Bounce<BigDecimal> = when (n) {
        BigDecimal(0) -> Done(acc)
        else -> More { factorial(n.dec(), acc * n) }
    }

    @Test
    fun run() {
        assertDoesNotThrow {
            trampoline(factorial(BigDecimal(100000)))
                .also { println(it) }
        }
    }
}
