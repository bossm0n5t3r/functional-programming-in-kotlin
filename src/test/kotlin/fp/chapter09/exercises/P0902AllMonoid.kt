package fp.chapter09.exercises

import fp.chapter09.Monoid
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AllMonoid : Monoid<Boolean> {
    override fun mempty() = true
    override fun mappend(m1: Boolean, m2: Boolean) = m1 && m2
}

class P0902AllMonoid {
    @Test
    fun run() {
        AllMonoid().run {
            assertTrue(mappend(true, mempty()))
            assertFalse(mappend(false, mempty()))
        }
    }
}
