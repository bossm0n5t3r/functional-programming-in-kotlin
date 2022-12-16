package fp.chapter11.exercises

import fp.chapter10.Just
import fp.chapter10.Maybe
import fp.chapter10.Nothing
import fp.chapter11.exception.Failure
import fp.chapter11.exception.Success
import fp.chapter11.exception.Try
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

fun <T> Try<T>.toMaybe(): Maybe<T> = when (this) {
    is Success -> Just(value)
    is Failure -> Nothing
}

class P1103ToMaybe {
    @Test
    fun run() {
        val result1 = Try.pure(10).fmap { it / 0 }.toMaybe()
        val result2 = Try.pure(10).fmap { it / 5 }.toMaybe()

        assertTrue(result1 is Nothing)
        assertTrue(result2 is Just)
    }
}
