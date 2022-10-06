package fp.chapter04.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0402Partial {
    private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial1(p1: P1): (P2, P3) -> R {
        return { p2, p3 -> this(p1, p2, p3) }
    }

    private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial2(p2: P2): (P1, P3) -> R {
        return { p1, p3 -> this(p1, p2, p3) }
    }

    private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).partial3(p3: P3): (P1, P2) -> R {
        return { p1, p2 -> this(p1, p2, p3) }
    }

    @Test
    fun run() {
        val func = { a: Int, b: Int, c: Int -> a + b + c }

        val partiallyAppliedFunc1 = func.partial1(1)
        assertEquals(partiallyAppliedFunc1(2, 3), 6)

        val partiallyAppliedFunc2 = func.partial2(2)
        assertEquals(partiallyAppliedFunc2(1, 3), 6)

        val partiallyAppliedFunc3 = func.partial3(3)
        assertEquals(partiallyAppliedFunc3(1, 2), 6)
    }
}
