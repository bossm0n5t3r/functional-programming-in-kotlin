package fp.chapter09.exercises

import fp.chapter05.funListOf
import fp.chapter09.mconcat
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class P0906AllMonoidWithMconcat {
    @Test
    fun run() {
        val x = funListOf(true, true, true)
        val y = funListOf(false, false, false)
        val z = funListOf(true, false, true)

        AllMonoid().run {
            assertTrue(mconcat(x))
            assertFalse(mconcat(y))
            assertFalse(mconcat(z))
        }
    }
}
