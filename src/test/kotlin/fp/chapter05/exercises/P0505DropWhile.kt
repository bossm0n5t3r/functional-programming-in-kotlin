package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.FunList.Cons
import fp.chapter05.FunList.Nil
import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0505DropWhile {
    private tailrec fun <T> FunList<T>.dropWhile(p: (T) -> Boolean): FunList<T> = when (this) {
        is Nil -> this
        is Cons -> if (p(head)) this else tail.dropWhile(p)
    }

    @Test
    fun run() {
        val intList = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
        assertEquals(intList.dropWhile { it % 2 == 0 }, funListOf(2, 3, 4))
        assertEquals(intList.dropWhile { it > 3 }, funListOf(4))
        assertEquals(intList.dropWhile { it > 5 }, Nil)
    }
}
