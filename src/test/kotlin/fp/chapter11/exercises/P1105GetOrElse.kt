package fp.chapter11.exercises

import fp.chapter11.exception.Failure
import fp.chapter11.exception.Success
import fp.chapter11.exception.Try
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

fun <T> Try<T>.getOrElse(default: T): T = when (this) {
    is Success -> value
    is Failure -> default
}

class P1105GetOrElse {
    @Test
    fun run() {
        val result1 = Try.pure(10).fmap { it / 0 }.getOrElse(100)
        val result2 = Try.pure(10).fmap { it / 5 }.getOrElse(100)

        assertTrue(result1 == 100)
        assertTrue(result2 == 2)
    }
}
