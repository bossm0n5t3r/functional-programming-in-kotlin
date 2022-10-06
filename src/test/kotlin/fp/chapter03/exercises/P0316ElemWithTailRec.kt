package fp.chapter03.exercises

import fp.head
import fp.tail
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0316ElemWithTailRec {
    private tailrec fun elem(num: Int, list: List<Int>): Boolean = when {
        list.isEmpty() -> false
        num == list.head() -> true
        else -> elem(num, list.tail())
    }

    @Test
    fun run() {
        assertEquals(elem(3, listOf(1, 2, 3, 4, 5)), true)
        assertEquals(elem(10, listOf(1, 2, 3, 4, 5)), false)
    }
}
