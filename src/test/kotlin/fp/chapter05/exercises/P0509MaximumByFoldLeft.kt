package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.foldLeft
import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0509MaximumByFoldLeft {
    private fun FunList<Int>.maximumByFoldLeft(): Int =
        foldLeft(0) { acc, elm -> acc.coerceAtLeast(elm) }

    @Test
    fun run() {
        val list = funListOf(1, 2, 3, 4, 5)
        assertEquals(list.maximumByFoldLeft(), 5)
    }
}
