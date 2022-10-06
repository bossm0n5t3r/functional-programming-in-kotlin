package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0309GCD {
    private fun gcd(m: Int, n: Int): Int = when (n) {
        0 -> m
        else -> gcd(n, m % n)
    }

    @Test
    fun run() {
        assertEquals(gcd(24, 6), 6)
    }
}
