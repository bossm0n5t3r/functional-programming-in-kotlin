package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.FunList.Cons
import fp.chapter05.FunList.Nil
import fp.chapter05.addHead
import fp.chapter05.funListOf
import fp.chapter05.getHead
import fp.chapter05.getTail
import fp.chapter05.reverse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0506Take {
    private tailrec fun <T> FunList<T>.take(n: Int, acc: FunList<T> = Nil): FunList<T> = when {
        n < 0 -> throw IllegalArgumentException()
        n == 0 || this === Nil -> acc.reverse()
        else -> getTail().take(n - 1, acc.addHead(getHead()))
    }

    @Test
    fun run() {
        val intList = Cons(1, Cons(2, Cons(3, Nil)))
        assertEquals(intList.take(0), Nil)
        assertEquals(intList.take(1), funListOf(1))
        assertEquals(intList.take(2), funListOf(1, 2))
        assertEquals(intList.take(3), funListOf(1, 2, 3))
        assertEquals(intList.take(4), funListOf(1, 2, 3))
    }
}
