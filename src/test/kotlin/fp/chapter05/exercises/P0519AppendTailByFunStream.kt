package fp.chapter05.exercises

import fp.chapter05.FunStream
import fp.chapter05.funStreamOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0519AppendTailByFunStream {
    private fun <T> FunStream<T>.appendTail(value: T): FunStream<T> = when (this) {
        is FunStream.Nil -> FunStream.Cons({ value }, { FunStream.Nil })
        is FunStream.Cons -> FunStream.Cons(head) { tail().appendTail(value) }
    }

    @Test
    fun appendTailTest() {
        assertEquals(
            funStreamOf(1, 2, 3, 4, 5).appendTail(6),
            funStreamOf(1, 2, 3, 4, 5, 6)
        )
    }
}
