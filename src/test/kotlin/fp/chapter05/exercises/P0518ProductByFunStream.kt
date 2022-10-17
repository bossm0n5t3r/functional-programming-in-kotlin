package fp.chapter05.exercises

import fp.chapter05.FunStream
import fp.chapter05.foldLeft
import fp.chapter05.foldRight
import fp.chapter05.funStreamOf
import fp.chapter05.getHead
import fp.chapter05.getTail
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0518ProductByFunStream {
    private fun FunStream<Int>.product(): Int = when (this) {
        is FunStream.Nil -> 1
        is FunStream.Cons -> getHead() * getTail().product()
    }

    @Test
    fun productTest() {
        assertEquals(funStreamOf(1, 2, 3, 4, 5).product(), 1 * 2 * 3 * 4 * 5)
    }

    private fun FunStream<Int>.productUsingFoldLeft(): Int =
        foldLeft(1) { acc, value -> acc * value }

    @Test
    fun productUsingFoldLeftTest() {
        assertEquals(funStreamOf(1, 2, 3, 4, 5).productUsingFoldLeft(), 1 * 2 * 3 * 4 * 5)
    }

    private fun FunStream<Int>.productUsingFoldRight(): Int =
        foldRight(1) { value, acc -> acc * value }

    @Test
    fun productUsingFoldRightTest() {
        assertEquals(funStreamOf(1, 2, 3, 4, 5).productUsingFoldRight(), 1 * 2 * 3 * 4 * 5)
    }
}
