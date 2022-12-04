package fp.chapter09.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0903AnyMonoidWithMonoidLaw {
    @Test
    fun run() {
        val x = true
        val y = false
        val z = true

        AnyMonoid().run {
            assertEquals(mappend(x, mempty()), x)
            assertEquals(mappend(mempty(), x), x)
            assertEquals(mappend(x, mappend(y, z)), mappend(mappend(x, y), z))
        }
    }
}
