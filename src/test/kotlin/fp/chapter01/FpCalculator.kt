package fp.chapter01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FpCalculator {
    private fun calculate(calculator: (Int, Int) -> Int, num1: Int, num2: Int): Int = calculator(num1, num2)

    @Test
    fun run() {
        assertEquals(calculate({ n1, n2 -> n1 + n2 }, 3, 1), 4)
        assertEquals(calculate({ n1, n2 -> n1 - n2 }, 3, 1), 2)
    }
}
