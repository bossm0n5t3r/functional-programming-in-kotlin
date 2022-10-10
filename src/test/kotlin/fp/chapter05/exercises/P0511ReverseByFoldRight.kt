package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.FunList.Nil
import fp.chapter05.appendTail
import fp.chapter05.foldRight
import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0511ReverseByFoldRight {
    private fun <T> FunList<T>.reverseByFoldRight(): FunList<T> =
        foldRight(Nil) { elm, acc: FunList<T> -> acc.appendTail(elm) }

    @Test
    fun run() {
        val list = funListOf(1, 2, 3, 4, 5)
        assertEquals(list.reverseByFoldRight(), funListOf(5, 4, 3, 2, 1))
    }
}
