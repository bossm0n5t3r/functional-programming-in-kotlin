package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.FunList.Cons
import fp.chapter05.FunList.Nil
import fp.chapter05.addHead
import fp.chapter05.funListOf
import fp.chapter05.reverse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0507TakeWhile {
    private tailrec fun <T> FunList<T>.takeWhile(acc: FunList<T> = Nil, p: (T) -> Boolean): FunList<T> = when (this) {
        Nil -> acc.reverse()
        is Cons -> when (p(head)) {
            false -> acc.reverse()
            true -> tail.takeWhile(acc.addHead(head), p)
        }
    }

    @Test
    fun run() {
        val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
        assertEquals(intList.takeWhile { it < 4 }, funListOf(1, 2, 3))
        assertEquals(intList.takeWhile { it < 2 }, funListOf(1))
        assertEquals(intList.takeWhile { it < 0 }, Nil)
    }
}
