package fp.chapter09

import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SumMonoid : Monoid<Int> {
    override fun mempty(): Int = 0
    override fun mappend(m1: Int, m2: Int): Int = m1 + m2
}

class SumMonoidTest {
    @Test
    fun run() {
        val x = 1
        val y = 2
        val z = 3

        SumMonoid().run {
            assertEquals(mappend(mempty(), x), x)
            assertEquals(mappend(x, mempty()), x)
            assertEquals(mappend(mappend(x, y), z), mappend(x, mappend(y, z)))
        }

        assertEquals(SumMonoid().mconcat(funListOf(1, 2, 3, 4, 5)), 15)
    }
}
