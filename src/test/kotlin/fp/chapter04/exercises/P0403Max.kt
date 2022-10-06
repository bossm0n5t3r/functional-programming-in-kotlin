package fp.chapter04.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0403Max {
    private fun max(a: Int) = { b: Int -> if (a >= b) a else b }

    @Test
    fun run() {
        assertEquals(max(1)(2), 2)
        assertEquals(max(2)(1), 2)
    }
}
