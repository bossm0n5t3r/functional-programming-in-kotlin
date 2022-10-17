package fp.chapter05.exercises

import fp.chapter05.FunStream
import fp.chapter05.funStreamOf
import fp.chapter05.getHead
import fp.chapter05.toFunStream
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T, R> FunStream<T>.map(f: (T) -> R): FunStream<R> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> FunStream.Cons({ f(head()) }, { tail().map(f) })
}

class P0521MapByFunStream {
    @Test
    fun mapTest() {
        assertEquals(
            funStreamOf(1, 2, 3, 4, 5).map { it * 2 },
            funStreamOf(2, 4, 6, 8, 10)
        )

        assertEquals(
            (1..100000000)
                .toFunStream()
                .map { it * it }
                .filter { it > 100 }
                .getHead(),
            121
        )
    }
}
