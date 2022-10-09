package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.FunList.Cons
import fp.chapter05.FunList.Nil
import fp.chapter05.addHead
import fp.chapter05.funListOf
import fp.chapter05.reverse
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0508IndexedMap {
    private tailrec fun <T, R> FunList<T>.indexedMap(
        index: Int = 0,
        acc: FunList<R> = Nil,
        f: (Int, T) -> R,
    ): FunList<R> = when (this) {
        is Nil -> acc.reverse()
        is Cons -> tail.indexedMap(
            index = index + 1,
            acc = acc.addHead(f(index, head)),
            f = f,
        )
    }

    @Test
    fun run() {
        val intList = Cons(1, Cons(5, Cons(3, Cons(2, Nil))))
        assertEquals(
            intList.indexedMap { index, elm -> index * elm },
            funListOf(0, 5, 6, 6)
        )
    }
}
