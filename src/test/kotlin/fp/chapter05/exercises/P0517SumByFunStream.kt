package fp.chapter05.exercises

import fp.chapter05.FunStream
import fp.chapter05.foldLeft
import fp.chapter05.foldRight
import fp.chapter05.funStreamOf
import fp.chapter05.getHead
import fp.chapter05.getTail
import fp.chapter05.toFunStream
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@Disabled("Prevent java.lang.StackOverflowError")
class P0517SumByFunStream {
    private fun FunStream<Int>.sum(): Int = when (this) {
        is FunStream.Nil -> 0
        is FunStream.Cons -> getHead() + getTail().sum()
    }

    @Test
    fun sumTest() {
        assertEquals(funStreamOf(1, 2, 3, 4, 5).sum(), 1 + 2 + 3 + 4 + 5)
        assertEquals((1..10000).toFunStream().sum(), 50005000)
    }

    private fun FunStream<Int>.sumUsingFoldLeft(): Int =
        this.foldLeft(0) { acc, value -> acc + value }

    @Test
    fun sumUsingFoldLeftTest() {
        assertEquals(funStreamOf(1, 2, 3, 4, 5).sumUsingFoldLeft(), 1 + 2 + 3 + 4 + 5)
        assertEquals((1..10000).toFunStream().sumUsingFoldLeft(), 50005000)
    }

    private fun FunStream<Int>.sumUsingFoldRight(): Int =
        this.foldRight(0) { value, acc -> acc + value }

    @Test
    fun sumUsingFoldRightTest() {
        assertEquals(funStreamOf(1, 2, 3, 4, 5).sumUsingFoldRight(), 1 + 2 + 3 + 4 + 5)
        assertEquals((1..10000).toFunStream().sumUsingFoldRight(), 50005000)
    }
}
