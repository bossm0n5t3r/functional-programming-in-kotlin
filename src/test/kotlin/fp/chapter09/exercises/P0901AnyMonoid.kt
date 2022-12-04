package fp.chapter09.exercises

import fp.chapter09.Monoid
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class P0901AnyMonoid {
    class AnyMonoid : Monoid<Boolean> {
        override fun mempty() = false
        override fun mappend(m1: Boolean, m2: Boolean) = m1 || m2
    }

    @Test
    fun run() {
        AnyMonoid().run {
            assertTrue(mappend(true, mempty()))
            assertFalse(mappend(false, mempty()))
        }
    }
}
