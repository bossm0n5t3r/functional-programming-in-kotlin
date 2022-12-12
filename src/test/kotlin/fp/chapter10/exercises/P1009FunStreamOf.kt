package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T> funStreamOf(vararg elements: T): FunStream<T> = elements.toFunStream()

private fun <T> Array<out T>.toFunStream(): FunStream<T> = when {
    this.isEmpty() -> FunStream.Nil
    else -> FunStream.Cons({ this[0] }, { this.copyOfRange(1, this.size).toFunStream() })
}

class P1009FunStreamOf {
    @Test
    fun run() {
        val funStream: FunStream<Int> = funStreamOf(1, 2, 3)
        assertEquals(
            funStream,
            FunStream.Cons({ 1 }, { FunStream.Cons({ 2 }, { FunStream.Cons({ 3 }, { FunStream.Nil }) }) })
        )

        val nilStream = FunStream.Nil
        assertEquals(nilStream, FunStream.Nil)
    }
}
