package fp.chapter08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class AMaybe<out A> : Applicative<A> {

    companion object {
        fun <V> pure(value: V): Applicative<V> = AJust(0).pure(value)
    }

    override fun <V> pure(value: V): Applicative<V> = AJust(value)

    abstract override fun <B> apply(ff: Applicative<(A) -> B>): AMaybe<B>
}

data class AJust<out A>(val value: A) : AMaybe<A>() {

    override fun toString(): String = "AJust($value)"

    override fun <B> apply(ff: Applicative<(A) -> B>): AMaybe<B> = when (ff) {
        is AJust -> fmap(ff.value)
        else -> ANothing
    }

    override fun <B> fmap(f: (A) -> B): AMaybe<B> = AJust(f(value))
}

object ANothing : AMaybe<kotlin.Nothing>() {

    override fun toString(): String = "ANothing"

    override fun <B> apply(ff: Applicative<(kotlin.Nothing) -> B>): AMaybe<B> = ANothing

    override fun <B> fmap(f: (kotlin.Nothing) -> B): AMaybe<B> = ANothing
}

class AMaybeTest {
    @Test
    fun fmapTest() {
        assertEquals(AJust(10).fmap { it + 10 }, AJust(20))
        assertEquals(ANothing.fmap { it: Int -> it + 10 }, ANothing)
    }

    @Test
    fun pureTest() {
        assertEquals(AMaybe.pure(10), AJust(10))
    }

    @Test
    fun applyTest() {
        assertEquals(AJust(10) apply AJust { x: Int -> x * 2 }, AJust(20))
        assertEquals(ANothing apply AJust { x: Int -> x * 2 }, ANothing)
        assertEquals(AJust(10).apply(AJust { x: Int -> x * 2 }), AJust(20))
    }

    @Test
    fun applicativeStyleProgrammingTest() {
        assertEquals(
            AMaybe.pure(10)
                apply AJust { x: Int -> x * 2 }
                apply AJust { x: Int -> x + 10 },
            AJust(30)
        )

        //    println(AMaybe.pure({ x: Int -> x * 2 })
        //            apply AJust(5)
        //            apply AJust(10))    // compile error
    }
}
