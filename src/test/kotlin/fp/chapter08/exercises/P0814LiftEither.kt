package fp.chapter08.exercises

import fp.chapter08.Either
import fp.chapter08.Right
import fp.chapter08.apply
import fp.chapter08.pure
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0814LiftEither {
    private fun <A, B, R> liftA2(
        binaryFunction: (A, B) -> R
    ): (Either<Nothing, A>, Either<Nothing, B>) -> Either<Nothing, R> =
        { f1: Either<Nothing, A>, f2: Either<Nothing, B> -> Either.pure(binaryFunction.curried()) apply f1 apply f2 }

    @Test
    fun run() {
        val lifted =
            liftA2 { x: Int, y: Int -> x + y }
        assertEquals(lifted(Right(1), Right(2)), Right(3))

        val lifted2 =
            liftA2 { x: String, y: String -> x + y }
        assertEquals(lifted2(Right("Hello, "), Right("Kotlin")), Right("Hello, Kotlin"))

        val lifted3 =
            liftA2 { x: Int, y: String -> x.toString() + y }
        assertEquals(lifted3(Right(10), Right("Kotlin")), Right("10Kotlin"))
    }
}
