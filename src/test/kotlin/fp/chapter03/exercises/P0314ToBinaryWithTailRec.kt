package fp.chapter03.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0314ToBinaryWithTailRec {
    private tailrec fun toBinary(n: Int, acc: String = ""): String = when {
        n < 2 -> "$n$acc"
        else -> toBinary(n / 2, "${n % 2}$acc")
    }

    @Test
    fun run() {
        assertEquals(toBinary(10), "1010")
        assertEquals(toBinary(27), "11011")
        assertEquals(toBinary(255), "11111111")
    }
}
