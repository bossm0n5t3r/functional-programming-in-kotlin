package fp.chapter10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class Maybe<out A> : Monad<A> {

    companion object {
        fun <V> pure(value: V): Maybe<V> = Just(0).pure(value)
    }

    override fun <V> pure(value: V): Maybe<V> = Just(value)

    override fun <B> fmap(f: (A) -> B): Maybe<B> = super.fmap(f) as Maybe<B>

    override infix fun <B> flatMap(f: (A) -> Monad<B>): Maybe<B> = when (this) {
        is Just -> try {
            f(value) as Maybe<B>
        } catch (e: ClassCastException) {
            Nothing
        }

        is Nothing -> Nothing
    }
}

data class Just<out A>(val value: A) : Maybe<A>() {

    override fun toString(): String = "Just($value)"
}

object Nothing : Maybe<kotlin.Nothing>() {

    override fun toString(): String = "Nothing"
}

infix fun <A, B> Maybe<(A) -> B>.apply(f: Maybe<A>): Maybe<B> = when (this) {
    is Just -> f.fmap(value)
    is Nothing -> Nothing
}

private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
    { p1: P1 -> { p2: P2 -> this(p1, p2) } }

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R =
    { p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } } }

class MaybeMonad {
    @Test
    fun fmapTest() {
        assertEquals(Just(10).fmap { it + 10 }, Just(20))
        assertEquals(Nothing.fmap { x: Int -> x + 10 }, Nothing)
    }

    @Test
    fun pureTest() {
        assertEquals(Maybe.pure(10), Just(10))
        assertEquals(
            Maybe.pure { x: Int -> x * 2 }::class,
            Just { x: Int -> x * 2 }::class,
        )
    }

    @Test
    fun applyTest() {
        assertEquals(Maybe.pure { x: Int -> x * 2 } apply Just(10), Just(20))
        assertEquals(Maybe.pure { x: Int -> x * 2 } apply Nothing, Nothing)
        assertEquals(
            Maybe.pure({ x: Int, y: Int -> x * y }.curried())
                apply Just(10)
                apply Just(20),
            Just(200)
        )

        assertEquals(
            Maybe.pure({ x: Int, y: Int, z: Int -> x * y + z }.curried())
                apply Just(10)
                apply Just(20)
                apply Just(30),
            Just(230)
        )
    }

    @Test
    fun leadToTest() {
        assertEquals(Just(10).leadTo(Nothing), Nothing)
        assertEquals(Nothing.leadTo(Just(10)), Nothing)
        assertEquals(Just(10).leadTo(Just(20)), Just(20))
    }

    @Test
    fun flatMapTest() {
        assertEquals(Just(10).flatMap { x -> Maybe.pure(x * 2) }, Just(20))
        assertEquals(Nothing.flatMap { x: Int -> Maybe.pure(x * 2) }, Nothing)
        assertEquals(Just(Just(10)).flatMap { m -> m.fmap { x -> x * 2 } }, Just(20))
    }
}
