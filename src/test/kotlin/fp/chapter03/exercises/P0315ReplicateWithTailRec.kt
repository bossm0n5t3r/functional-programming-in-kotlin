package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0315ReplicateWithTailRec {
    private tailrec fun replicate(n: Int, element: Int, acc: List<Int> = emptyList()): List<Int> = when {
        n <= 0 -> acc
        else -> replicate(n - 1, element, listOf(element) + acc)
    }

    @Test
    fun run() {
        assertEquals(replicate(3, 5), listOf(5, 5, 5))
    }
}
