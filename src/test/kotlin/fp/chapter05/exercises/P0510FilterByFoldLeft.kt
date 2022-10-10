package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.FunList.Nil
import fp.chapter05.appendTail
import fp.chapter05.foldLeft
import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0510FilterByFoldLeft {
    private fun <T> FunList<T>.filterByFoldLeft(p: (T) -> Boolean): FunList<T> =
        foldLeft(Nil) { acc: FunList<T>, elm ->
            if (p(elm)) {
                acc.appendTail(elm)
            } else {
                acc
            }
        }

    @Test
    fun run() {
        val list = funListOf(1, 2, 3, 4, 5)
        assertEquals(list.filterByFoldLeft { it % 2 == 0 }, funListOf(2, 4))
    }
}
