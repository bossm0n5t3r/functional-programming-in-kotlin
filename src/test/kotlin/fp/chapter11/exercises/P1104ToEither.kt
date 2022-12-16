package fp.chapter11.exercises

import fp.chapter11.exception.Either
import fp.chapter11.exception.Failure
import fp.chapter11.exception.Left
import fp.chapter11.exception.Right
import fp.chapter11.exception.Success
import fp.chapter11.exception.Try
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

fun <T> Try<T>.toEither(): Either<Throwable, T> = when (this) {
    is Success -> Right(value)
    is Failure -> Left(e)
}

class P1104ToEither {
    @Test
    fun run() {
        val result1 = Try.pure(10).fmap { it / 0 }.toEither()
        val result2 = Try.pure(10).fmap { it / 5 }.toEither()

        assertTrue(result1 is Left)
        assertTrue(result2 is Right)
    }
}
