package fp.chapter01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalculatorWithOOP {
    interface Calculator {
        fun calculate(num1: Int, num2: Int): Int
    }

    class Plus : Calculator {
        override fun calculate(num1: Int, num2: Int): Int {
            return num1 + num2
        }
    }

    class Minus : Calculator {
        override fun calculate(num1: Int, num2: Int): Int {
            return num1 - num2
        }
    }

    class OopCalculator(private val calculator: Calculator) {
        fun calculate(num1: Int, num2: Int): Int = calculator.calculate(num1, num2)
    }

    @Test
    fun run() {
        val plusCalculator = OopCalculator(Plus())
        assertEquals(plusCalculator.calculate(3, 1), 4)

        val minusCalculator = OopCalculator(Minus())
        assertEquals(minusCalculator.calculate(3, 1), 2)
    }
}
