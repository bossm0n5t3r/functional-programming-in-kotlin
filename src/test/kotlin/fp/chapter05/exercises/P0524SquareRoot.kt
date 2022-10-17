package fp.chapter05.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0524SquareRoot {
    private tailrec fun squareRoot(num: Int = 1, acc: Int = 0): Int = when {
        acc > 1000 -> num - 1
        else -> squareRoot(num + 1, acc + num * num)
    }

    @Test
    fun squareRootTest() {
        assertEquals(squareRoot(), 14)
    }
}
