package fp.chapter07

import fp.compose
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class WrongFunctor {
    sealed class MaybeCounter<out A> : Functor<A> {
        abstract override fun toString(): String
        abstract override fun <B> fmap(f: (A) -> B): MaybeCounter<B>
    }

    data class JustCounter<out A>(val value: A, val count: Int) : MaybeCounter<A>() {
        override fun toString(): String = "JustCounter($value, $count)"
        override fun <B> fmap(f: (A) -> B): MaybeCounter<B> = JustCounter(f(value), count + 1)
    }

    object NothingCounter : MaybeCounter<kotlin.Nothing>() {
        override fun toString(): String = "NothingCounter"
        override fun <B> fmap(f: (kotlin.Nothing) -> B): MaybeCounter<B> = NothingCounter
    }

    @Test
    fun maybeCounterTest() {
        assertEquals(
            JustCounter(10, 3)
                .fmap { it + 10 }
                .fmap { it * 2 },
            JustCounter(40, 5)
        )
        assertEquals(NothingCounter.fmap { tmpInt: Int -> tmpInt + 10 }, NothingCounter)
    }

    @Test
    fun verifyLawsOfFunctorOfMaybeCounter() {
        assertEquals(
            NothingCounter.fmap { nothingCounter: NothingCounter -> identity(nothingCounter) },
            identity(NothingCounter)
        )
        assertNotEquals(
            JustCounter(5, 0).fmap { identity(it) },
            identity(JustCounter(5, 0))
        )

        val f: (Int) -> Int = { it + 1 }
        val g: (Int) -> Int = { it * 2 }

        val nothingLeft = NothingCounter.fmap(f compose g)
        val nothingRight = NothingCounter.fmap(g).fmap(f)
        assertEquals(nothingLeft, nothingRight)

        val justLeft = JustCounter(5, 0).fmap(f compose g)
        val justRight = JustCounter(5, 0).fmap(g).fmap(f)
        assertNotEquals(justLeft, justRight)
    }
}
