package fp.chapter05.exercises

import fp.chapter05.FunStream
import fp.chapter05.dropWhile
import fp.chapter05.funStreamOf
import fp.chapter05.getHead
import fp.chapter05.getTail
import fp.chapter05.toFunStream
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T> FunStream<T>.filter(p: (T) -> Boolean): FunStream<T> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> {
        val first = dropWhile(p)
        if (first != FunStream.Nil) {
            FunStream.Cons({ first.getHead() }, { first.getTail().filter(p) })
        } else {
            FunStream.Nil
        }
    }
}

class P0520FilterByFunStream {
    @Test
    fun filterTest() {
        assertEquals(
            funStreamOf(1, 2, 3, 4, 5).filter { it % 2 == 0 },
            funStreamOf(2, 4)
        )

        assertEquals(
            funStreamOf(1, 2, 3, 4, 5).filter { it > 6 },
            FunStream.Nil
        )

        assertEquals(
            (1..100000000)
                .toFunStream()
                .filter { it > 100 }
                .getHead(),
            101
        )
    }
}
