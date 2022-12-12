package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T> mempty(): FunStream<T> = FunStream.Nil

infix fun <A> FunStream<A>.mappend(other: FunStream<A>): FunStream<A> = when {
    this is FunStream.Nil -> other
    other is FunStream.Nil -> this
    this is FunStream.Cons && other is FunStream.Cons -> FunStream.Cons({ head() }, { tail().mappend(other) })
    else -> FunStream.Nil
}

class P1011FunStreamMonoid {
    @Test
    fun run() {
        val funStream: FunStream<Int> = funStreamOf(1, 2, 3)
        val funStream2: FunStream<Int> = funStreamOf(4, 5, 6)

        assertEquals(funStream mappend funStream2, funStreamOf(1, 2, 3, 4, 5, 6))
        assertEquals(funStream mappend mempty(), funStreamOf(1, 2, 3))
        assertEquals(mempty<Int>() mappend funStream, funStreamOf(1, 2, 3))
        assertEquals(mempty<Int>() mappend mempty(), mempty<Int>())
    }
}
