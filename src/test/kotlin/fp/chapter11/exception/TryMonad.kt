package fp.chapter11.exception

import fp.chapter10.Monad
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class Try<out R> : Monad<R> {

    companion object {
        fun <V> pure(value: V) = Success(0).pure(value)
    }

    override fun <B> fmap(f: (R) -> B): Try<B> = super.fmap(f) as Try<B>

    override fun <V> pure(value: V): Try<V> = Success(value)

    override fun <R2> flatMap(f: (R) -> Monad<R2>): Try<R2> = when (this) {
        is Failure -> Failure(e)
        is Success -> try {
            f(value) as Try<R2>
        } catch (e: Throwable) {
            Failure(e)
        }
    }
}

data class Failure(val e: Throwable) : Try<Nothing>() {
    override fun toString(): String = "Failure(${e.message})"
}

data class Success<out R>(val value: R) : Try<R>() {
    override fun toString(): String = "Success($value)"
}

infix fun <T, R> Try<(T) -> R>.apply(f: Try<T>): Try<R> = when (this) {
    is Failure -> Failure(e)
    is Success -> f.fmap(value)
}

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
    { p1: P1 -> { p2: P2 -> this(p1, p2) } }

class TryMonad {
    @Test
    fun fmapTest() {
        assertEquals(Success(10).fmap { it + 10 }, Success(20))
        assertThat(Failure(NullPointerException("NPE")).fmap { x: Int -> x + 10 })
            .isExactlyInstanceOf(Failure(NullPointerException())::class.java)
    }

    @Test
    fun pureTest() {
        assertEquals(Try.pure(10), Success(10))
        assertThat(Try.pure { x: Int -> x * 2 })
            .isExactlyInstanceOf(Success { x: Int -> x * 2 }::class.java)
            .isExactlyInstanceOf(Success { x: Int -> x + 1 }::class.java)
            .isExactlyInstanceOf(Success { x: Int -> x }::class.java) // Success((kotlin.Int) -> kotlin.Int)
    }

    @Test
    fun applyTest() {
        assertThat(Try.pure { x: Int -> x * 2 } apply Failure(NullPointerException("NPE")))
            .isExactlyInstanceOf(Failure(NullPointerException())::class.java)
        assertEquals(Try.pure { x: Int -> x * 2 } apply Success(10), Success(20))
        assertThat(
            Try.pure({ x: Int, y: Int -> x * y }.curried())
                apply Failure(NullPointerException("NPE"))
                apply Success(10)
        ).isExactlyInstanceOf(Failure(NullPointerException())::class.java)
        assertEquals(
            Try.pure({ x: Int, y: Int -> x * y }.curried())
                apply Success(10)
                apply Success(20),
            Success(200)
        )
    }

    @Test
    fun flapMapTest() {
        assertEquals(Success(10).flatMap { x -> Try.pure(x * 2) }, Success(20))
        assertThat(Failure(NullPointerException("NPE")).flatMap { x: Int -> Try.pure(x * 2) })
            .isExactlyInstanceOf(Failure(NullPointerException())::class.java)
        assertEquals(
            Success(Success(10)).flatMap { m -> m.fmap { x -> x * 2 } },
            Success(20)
        )
    }
}
