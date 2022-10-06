package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0304ToBinary {
    private fun toBinary(n: Int): String = when {
        n < 2 -> n.toString()
        else -> "${toBinary(n / 2)}${n % 2}"
    }

    @Test
    fun run() {
        assertEquals(toBinary(10), "1010")
        assertEquals(toBinary(27), "11011")
        assertEquals(toBinary(255), "11111111")
    }
}
