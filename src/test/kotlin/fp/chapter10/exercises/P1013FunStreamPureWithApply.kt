package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T> pure(value: T): FunStream<T> = FunStream.Cons({ value }, { FunStream.Nil })

infix fun <A, B> FunStream<(A) -> B>.apply(f: FunStream<A>): FunStream<B> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> f.fmap(head()) mappend tail().apply(f)
}

class P1013FunStreamPureWithApply {
    @Test
    fun run() {
        val result = pure(1)
        assertEquals(result, funStreamOf(1))

        val funStream: FunStream<(Int) -> Int> = funStreamOf({ x -> x }, { x -> x * 2 }, { x -> x * 3 })
        val valueStream: FunStream<Int> = funStreamOf(1, 2, 3)
        val result2 = funStream apply valueStream

        assertEquals(result2, funStreamOf(1, 2, 3, 2, 4, 6, 3, 6, 9))
    }
}
