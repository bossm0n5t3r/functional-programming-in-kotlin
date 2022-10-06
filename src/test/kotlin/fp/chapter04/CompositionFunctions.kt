package fp.chapter04

import org.junit.jupiter.api.Test
import kotlin.math.abs
import kotlin.test.assertEquals

class CompositionFunctions {
    private fun composed(i: Int) = addThree(twice(i))

    private fun addThree(i: Int) = i + 3

    private fun twice(i: Int) = i * 2

    @Test
    fun compositionFunctionExample() {
        assertEquals(composed(3), 9)
    }

    private infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
        return { gInput: G -> this(g(gInput)) }
    }

    @Test
    fun composeExtensionFunction() {
        val addThree = { i: Int -> i + 3 }
        val twice = { i: Int -> i * 2 }
        val composedFunc = addThree compose twice
        assertEquals(composedFunc(3), 9)
    }

    private val absolute = { i: List<Int> -> i.map { abs(it) } }
    private val negative = { i: List<Int> -> i.map { -it } }
    private val minimum = { i: List<Int> -> i.min() }

    @Test
    fun compositionUsingParameters() {
        val result = minimum(negative(absolute(listOf(3, -1, 5, -2, -4, 8, 14))))
        assertEquals(result, -14)
    }

    /**
     * Point Free Style Programming
     */
    @Test
    fun compositionUsingComposeFunction() {
        val composed = minimum compose negative compose absolute
        val result = composed(listOf(3, -1, 5, -2, -4, 8, 14))
        assertEquals(result, -14)
    }

    private tailrec fun gcd(m: Int, n: Int): Int = when (n) {
        0 -> m
        else -> gcd(n, m % n)
    }

    private tailrec fun power(x: Double, n: Int, acc: Double = 1.0): Double = when (n) {
        0 -> acc
        else -> power(x, n - 1, x * acc)
    }

    private val powerOfTwo = { x: Int -> power(x.toDouble(), 2).toInt() }

    @Test
    fun gcdPowerOfTwo() {
        val gcdPowerOfTwo = { x1: Int, x2: Int -> gcd(powerOfTwo(x1), powerOfTwo(x2)) }

        assertEquals(gcdPowerOfTwo(25, 5), 25)
    }

    private fun <P1, P2, R> ((P1, P2) -> R).curried(): (P1) -> (P2) -> R = { p1: P1 -> { p2: P2 -> this(p1, p2) } }

    @Test
    fun composedGcdPowerOfTwoThatWrongCompositionExample() {
        val curriedGcd = ::gcd.curried()
        // 잘못된 합성의 예
        val composedGcdPowerOfTwo = curriedGcd compose powerOfTwo

        assertEquals(composedGcdPowerOfTwo(25)(5), 5)
    }

    @Test
    fun composedGcdPowerOfTwoThatRightCompositionExample() {
        val curriedGcd = { m: Int, n: Int -> gcd(m, powerOfTwo(n)) }.curried()
        // 적절한 합성의 예
        val composedGcdPowerOfTwo = curriedGcd compose powerOfTwo

        assertEquals(composedGcdPowerOfTwo(25)(5), 25)
    }
}
