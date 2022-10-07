package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.FunList.Cons
import fp.chapter05.FunList.Nil
import fp.chapter05.funListOf
import fp.chapter05.getTail
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0504Drop {
    private tailrec fun <T> FunList<T>.drop(n: Int): FunList<T> = when {
        n < 0 -> throw NoSuchElementException()
        n == 0 || this === Nil -> this
        else -> getTail().drop(n - 1)
    }

    @Test
    fun run() {
        val intList = Cons(1, Cons(2, Cons(3, Nil)))
        assertEquals(intList.drop(1), funListOf(2, 3))
        assertEquals(intList.drop(2), funListOf(3))
        assertEquals(intList.drop(3), Nil)
        assertEquals(intList.drop(4), Nil)
    }
}
