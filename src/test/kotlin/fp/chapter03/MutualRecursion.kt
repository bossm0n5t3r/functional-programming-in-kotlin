package fp.chapter03

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Disabled("Prevent java.lang.StackOverflowError")
class MutualRecursion {
    private fun even(n: Int): Boolean = when (n) {
        0 -> true
        else -> odd(n - 1)
    }

    private fun odd(n: Int): Boolean = when (n) {
        0 -> false
        else -> even(n - 1)
    }

    @Test
    fun run() {
        assertEquals(even(9999), false)
        assertEquals(odd(9999), true)
    }
}
