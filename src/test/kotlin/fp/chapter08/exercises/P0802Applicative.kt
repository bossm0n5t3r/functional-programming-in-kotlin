package fp.chapter08.exercises

import fp.chapter08.Applicative
import fp.chapter08.exercises.AFunList.Companion.pure
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class AFunList<out A> : Applicative<A> {
    companion object {
        fun <V> pure(value: V): ACons<V> = ANil.pure(value)
    }

    abstract override fun <B> fmap(f: (A) -> B): AFunList<B>
    override fun <V> pure(value: V): ACons<V> = ACons(value, ANil)
    abstract override fun <B> apply(ff: Applicative<(A) -> B>): AFunList<B>
    abstract fun first(): A
    abstract fun size(): Int
}

object ANil : AFunList<Nothing>() {

    override fun <B> fmap(f: (Nothing) -> B): AFunList<B> = ANil

    override fun <B> apply(ff: Applicative<(Nothing) -> B>): AFunList<B> = ANil

    override fun first(): Nothing = throw NoSuchElementException()

    override fun size(): Int = 0
}

data class ACons<A>(val head: A, val tail: AFunList<A>) : AFunList<A>() {

    override fun <B> fmap(f: (A) -> B): AFunList<B> = ACons(f(head), tail.fmap(f))

    override infix fun <B> apply(ff: Applicative<(A) -> B>): AFunList<B> = when (ff) {
        is ACons -> ACons(ff.head(head), tail.apply(ff))
        else -> ANil
    }

    override fun first() = head

    override fun size(): Int = 1 + tail.size()
}

class P0802Applicative {
    @Test
    fun run() {
        assertEquals(pure(1), ACons(1, ANil))

        assertEquals(pure(1).fmap { it * 10 }, ACons(10, ANil))
        assertEquals(ANil.fmap { a: Int -> a * 10 }, ANil)

        assertEquals(pure(1).fmap { it * 10 }, ACons(10, ANil))
        assertEquals(ANil.fmap { a: Int -> a * 10 }, ANil)

        assertEquals(pure(1) apply pure { x: Int -> x * 10 }, ACons(10, ANil))
        assertEquals(ANil apply pure({ x: Int -> x * 10 }), ANil)

        assertEquals(
            ACons(
                1,
                ACons(
                    2,
                    ACons(
                        3,
                        ACons(
                            4,
                            ANil
                        )
                    )
                )
            ) apply pure { x: Int -> x * 10 },
            ACons(10, ACons(20, ACons(30, ACons(40, ANil))))
        )
    }
}
