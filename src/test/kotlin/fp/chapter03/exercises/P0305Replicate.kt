package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0305Replicate {
    private fun replicate(n: Int, element: Int): List<Int> = when {
        n <= 0 -> emptyList() // Add it just in case
        n == 1 -> listOf(element)
        else -> listOf(element) + replicate(n - 1, element)
    }

    @Test
    fun run() {
        assertEquals(replicate(3, 5), listOf(5, 5, 5))
    }
}
