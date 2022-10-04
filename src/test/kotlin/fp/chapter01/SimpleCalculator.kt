package fp.chapter01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SimpleCalculator {
    private fun calculate(operator: Char, num1: Int, num2: Int): Int = when (operator) {
        '+' -> num1 + num2
        '-' -> num1 - num2
        else -> throw IllegalArgumentException()
    }

    @Test
    fun run() {
        assertEquals(calculate('+', 3, 1), 4)
        assertEquals(calculate('-', 3, 1), 2)
    }
}
