package fp.chapter05.exercises

import fp.chapter05.FunStream
import fp.chapter05.funStreamOf
import fp.chapter05.generateFunStream
import fp.chapter05.getHead
import fp.chapter05.getTail
import fp.chapter05.toFunStream
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0522TakeByFunStream {
    private fun <T> FunStream<T>.take(n: Int): FunStream<T> = when {
        n < 0 -> throw IllegalArgumentException()
        n == 0 || this is FunStream.Nil -> FunStream.Nil
        else -> FunStream.Cons({ this.getHead() }, { this.getTail().take(n - 1) })
    }

    @Test
    fun takeTest() {
        assertEquals(
            (1..100000000)
                .toFunStream()
                .take(1)
                .getHead(),
            1
        )

        assertEquals(
            generateFunStream(0) { it + 5 }.take(5),
            funStreamOf(0, 5, 10, 15, 20)
        )
    }
}
