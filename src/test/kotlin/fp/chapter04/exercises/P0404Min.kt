package fp.chapter04.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0404Min {
    private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R =
        { p1: P1 -> { p2: P2 -> this(p1, p2) } }

    @Test
    fun run() {
        val min = { a: Int, b: Int -> if (a <= b) a else b }
        val minCurried = min.curried()

        assertEquals(minCurried(1)(2), 1)
        assertEquals(minCurried(2)(1), 1)
    }
}
