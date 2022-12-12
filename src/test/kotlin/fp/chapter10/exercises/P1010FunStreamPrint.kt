package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

tailrec fun <T, R> FunStream<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    is FunStream.Nil -> acc
    is FunStream.Cons -> tail().foldLeft(f(acc, head()), f)
}

private fun <T> FunStream<T>.toStringByFoldLeft(): String =
    "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"

fun <T> printFunStream(stream: FunStream<T>) = println(stream.toStringByFoldLeft())

class P1010FunStreamPrint {
    @Test
    fun run() {
        val funStream: FunStream<Int> = funStreamOf(1, 2, 3)
        funStream.run {
            printFunStream(this) // [1, 2, 3]
            assertEquals(this.toStringByFoldLeft(), "[1, 2, 3]")
        }

        val nilStream = FunStream.Nil
        nilStream.run {
            printFunStream(this) // []
            assertEquals(nilStream.toStringByFoldLeft(), "[]")
        }
    }
}
