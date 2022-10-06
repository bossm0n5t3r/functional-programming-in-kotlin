package fp.chapter04.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0405PowerWithMax {
    private val maximum = { i: List<Int> -> i.max() }
    private val power = { i: Int -> i * i }

    @Test
    fun run() {
        assertEquals(power(maximum(listOf(1, 2, 3, 4, 5, 6, 7))), 49)
        assertEquals(power(maximum(listOf(10, 2, 13, 4, 0, 6, 1))), 169)
    }
}
