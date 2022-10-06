package fp.chapter04

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CalculatorExample {
    interface Calculable {
        fun calc(x: Int, y: Int): Int
    }

    class Sum : Calculable {
        override fun calc(x: Int, y: Int): Int {
            return x + y
        }
    }

    class Minus : Calculable {
        override fun calc(x: Int, y: Int): Int {
            return x - y
        }
    }

    class Product : Calculable {
        override fun calc(x: Int, y: Int): Int {
            return x * y
        }
    }

    class TwiceSum : Calculable {
        override fun calc(x: Int, y: Int): Int {
            return (x + y) * 2
        }
    }

    @Test
    fun calculatorOOPTest() {
        val calcSum = Sum()
        val calcMinus = Minus()
        val calcProduct = Product()
        val calcTwiceSum = TwiceSum()

        assertEquals(calcSum.calc(1, 5), 6)
        assertEquals(calcMinus.calc(5, 2), 3)
        assertEquals(calcProduct.calc(4, 2), 8)
        assertEquals(calcTwiceSum.calc(8, 2), 20)
    }

    private fun higherOrder(func: (Int, Int) -> Int, x: Int, y: Int): Int = func(x, y)
    private val sum: (Int, Int) -> Int = { x, y -> x + y }
    private val product: (Int, Int) -> Int = { x, y -> x * y }
    private val minus: (Int, Int) -> Int = { x, y -> x - y }
    private val twiceSum: (Int, Int) -> Int = { x, y -> (x + y) * 2 }

    @Test
    fun calculatorHigherOrderFunctionTest() {
        assertEquals(higherOrder(sum, 1, 5), 6)
        assertEquals(higherOrder(minus, 5, 2), 3)
        assertEquals(higherOrder(product, 4, 2), 8)
        assertEquals(higherOrder(twiceSum, 8, 2), 20)
    }
}
