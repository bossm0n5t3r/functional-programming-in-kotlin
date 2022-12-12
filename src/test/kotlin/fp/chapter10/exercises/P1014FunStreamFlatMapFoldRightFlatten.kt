package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

fun <T> FunStream<FunStream<T>>.flatten(): FunStream<T> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> head() mappend tail().flatten()
}

fun <T, R> FunStream<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
    is FunStream.Nil -> acc
    is FunStream.Cons -> f(head(), tail().foldRight(acc, f))
}

infix fun <T, R> FunStream<T>.flatMap(f: (T) -> FunStream<R>): FunStream<R> = when (this) {
    is FunStream.Nil -> FunStream.Nil
    is FunStream.Cons -> f(head()) mappend tail().flatMap(f)
}

class P1014FunStreamFlatMapFoldRightFlatten {
    @Test
    fun run() {
        val valueStream: FunStream<Int> = funStreamOf(1, 2, 3)
        val functionStream: (Int) -> FunStream<Int> = { x ->
            funStreamOf(x, x * 2, x * 3)
        }
        val flatMapResult = valueStream flatMap functionStream
        assertEquals(flatMapResult, funStreamOf(1, 2, 3, 2, 4, 6, 3, 6, 9))

        val funStream: FunStream<FunStream<Int>> = funStreamOf(funStreamOf(1, 2), funStreamOf(3, 4), funStreamOf(5, 6))
        val flattenResult = funStream.flatten()
        assertEquals(flattenResult, funStreamOf(1, 2, 3, 4, 5, 6))

        val foldRightStream = funStreamOf(1, 2, 3)
        val foldRightResult = foldRightStream.foldRight(1) { x, acc -> x * acc }
        assertTrue(foldRightResult == 6)
    }
}
