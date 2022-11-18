package fp.chapter08.exercises

import fp.chapter08.Just
import fp.chapter08.Maybe
import fp.chapter08.apply
import fp.chapter08.pure
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0815TripleLift {
    private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1: P1 ->
        { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } }
    }

    private fun <A, B, C, R> liftA3(
        tripleFunction: (A, B, C) -> R
    ): (Maybe<A>, Maybe<B>, Maybe<C>) -> Maybe<R> =
        { f1: Maybe<A>, f2: Maybe<B>, f3: Maybe<C> -> Maybe.pure(tripleFunction.curried()) apply f1 apply f2 apply f3 }

    @Test
    fun run() {
        val lifted =
            liftA3 { x: Int, y: Int, z: Int -> x + y + z }
        assertEquals(lifted(Just(1), Just(2), Just(3)), Just(6))

        val lifted2 =
            liftA3 { x: String, y: String, z: String -> x + y + z }
        assertEquals(
            lifted2(Just("Hello, "), Just("Kotlin, "), Just("FP")),
            Just("Hello, Kotlin, FP"),
        )

        val lifted3 =
            liftA3 { x: Int, y: String, z: String -> x.toString() + y + z }
        assertEquals(
            lifted3(Just(10), Just("Hello, "), Just("Kotlin")),
            Just("10Hello, Kotlin"),
        )
    }
}
