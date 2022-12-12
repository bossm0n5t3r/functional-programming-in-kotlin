package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

infix fun <T, R> FunStream<T>.fmap(f: (T) -> R): FunStream<R> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> FunStream.Cons({ f(head()) }, { tail().fmap(f) })
}

class P1012FunStreamFmap {
    @Test
    fun run() {
        val funStream: FunStream<Int> = funStreamOf(1, 2, 3)
        val empty = mempty<Int>()
        val function: (Int) -> String = { "value : $it" }

        val result: FunStream<String> = funStream fmap function
        assertEquals(result, funStreamOf("value : 1", "value : 2", "value : 3"))

        val result2: FunStream<String> = empty fmap function
        assertEquals(result2, mempty())
    }
}
