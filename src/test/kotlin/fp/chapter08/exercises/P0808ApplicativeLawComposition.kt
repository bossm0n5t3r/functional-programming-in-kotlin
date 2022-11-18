package fp.chapter08.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0808ApplicativeLawComposition {
    private fun <P1, P2, P3> compose(): ((P2) -> P3, (P1) -> P2, P1) -> P3 =
        { f: (P2) -> P3, g: (P1) -> P2, v: P1 -> f(g(v)) }

    private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R =
        { p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } } }

    @Test
    fun run() {
        val af1: FunList<(Int) -> Int> = Cons<(Int) -> Int>({ x: Int -> x * 2 }, Nil)
        val af2: FunList<(Int) -> Int> = Cons<(Int) -> Int>({ x: Int -> x + 10 }, Nil)
        val af3: FunList<Int> = Cons(1, Cons(2, Nil))

        val leftApply = FunList.pure(compose<Int, Int, Int>().curried()) apply af1 apply af2 apply af3
        val rightApply = af1 apply (af2 apply af3)

        assertEquals(leftApply, rightApply)
    }
}
