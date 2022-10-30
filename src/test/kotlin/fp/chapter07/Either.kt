package fp.chapter07

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

private fun divideTenByN(n: Int): Either<String, Int> = try {
    Right(10 / n)
} catch (e: ArithmeticException) {
    Left("divide by zero")
}

sealed class Either<out L, out R> : Functor<R> {
    abstract override fun <R2> fmap(f: (R) -> R2): Either<L, R2>
}

data class Left<out L>(val value: L) : Either<L, kotlin.Nothing>() {
    override fun <R2> fmap(f: (kotlin.Nothing) -> R2): Either<L, R2> = this
}

data class Right<out R>(val value: R) : Either<kotlin.Nothing, R>() {
    override fun <R2> fmap(f: (R) -> R2): Either<kotlin.Nothing, R2> = Right(f(value))
}

class EitherFunctor {
    @Test
    fun run() {
        assertEquals(divideTenByN(5), Right(value = 2))
        assertEquals(divideTenByN(0), Left(value = "divide by zero"))
        assertEquals(divideTenByN(5).fmap { r -> r * 2 }, Right(value = 4))
        assertEquals(divideTenByN(0).fmap { r -> r * 2 }, Left(value = "divide by zero"))
    }
}
