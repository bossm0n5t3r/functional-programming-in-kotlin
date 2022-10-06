package fp.chapter03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MutualTailRecursionWithTrampoline {
    sealed class Bounce<A>
    data class Done<A>(val result: A) : Bounce<A>()
    data class More<A>(val thunk: () -> Bounce<A>) : Bounce<A>()

    private tailrec fun <A> trampoline(bounce: Bounce<A>): A = when (bounce) {
        is Done -> bounce.result
        is More -> trampoline(bounce.thunk())
    }

    private fun odd(n: Int): Bounce<Boolean> = when (n) {
        0 -> Done(false)
        else -> More { even(n - 1) }
    }

    private fun even(n: Int): Bounce<Boolean> = when (n) {
        0 -> Done(true)
        else -> More { odd(n - 1) }
    }

    @Test
    fun run() {
        assertEquals(trampoline(even(999999)), false)
        assertEquals(trampoline(odd(999999)), true)
    }
}
