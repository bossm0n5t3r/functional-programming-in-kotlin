package fp.chapter11.exception

import fp.chapter10.Monad
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class Either<out L, out R> : Monad<R> {

    companion object {
        fun <V> pure(value: V) = Right(0).pure(value)
    }

    override fun <V> pure(value: V): Either<L, V> = Right(value)

    override fun <R2> fmap(f: (R) -> R2): Either<L, R2> = when (this) {
        is Left -> Left(value)
        is Right -> Right(f(value))
    }

    override fun <B> flatMap(f: (R) -> Monad<B>): Monad<B> = when (this) {
        is Left -> Left(value)
        is Right -> f(value)
    }
}

data class Left<out L>(val value: L) : Either<L, Nothing>() {
    override fun toString(): String = "Left($value)"
}

data class Right<out R>(val value: R) : Either<Nothing, R>() {
    override fun toString(): String = "Right($value)"
}

infix fun <L, A, B> Either<L, (A) -> B>.apply(f: Either<L, A>): Either<L, B> = when (this) {
    is Left -> Left(value)
    is Right -> f.fmap(value)
}

class EitherMonad {
    @Test
    fun fmapTest() {
        assertEquals(Right(10).fmap { it * 2 }, Right(20))
        assertEquals(Left("error").fmap { x: String -> "$x log" }, Left("error"))
    }

    @Test
    fun flatMapTest() {
        assertEquals(Right(10).flatMap { x -> Either.pure(x * 2) }, Right(20))
        assertEquals(Left("error").flatMap { x: Int -> Either.pure(x * 2) }, Left("error"))
        assertEquals(Right(Right(10)).flatMap { m -> m.fmap { x -> x * 2 } }, Right(20))
    }
}
