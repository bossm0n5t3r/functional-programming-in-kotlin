package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.FunList.Nil
import fp.chapter05.addHead
import fp.chapter05.foldRight
import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0512FilterByFoldRight {
    private fun <T> FunList<T>.filterByFoldRight(p: (T) -> Boolean): FunList<T> =
        foldRight(Nil) { elm, acc: FunList<T> ->
            if (p(elm)) {
                acc.addHead(elm)
            } else {
                acc
            }
        }

    @Test
    fun run() {
        val list = funListOf(1, 2, 3, 4, 5)
        assertEquals(list.filterByFoldRight { it % 2 == 0 }, funListOf(2, 4))
        assertEquals(list.filterByFoldRight { it < 1 }, Nil)
        assertEquals(list.filterByFoldRight { it < 6 }, funListOf(1, 2, 3, 4, 5))
    }
}
