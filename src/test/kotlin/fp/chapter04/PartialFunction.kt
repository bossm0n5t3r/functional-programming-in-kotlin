package fp.chapter04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.random.Random
import kotlin.test.assertEquals

class PartialFunction {
    private fun twice(x: Int) = x * 2

    private fun partialTwice(x: Int): Int =
        if (x < 100) {
            x * 2
        } else {
            throw IllegalArgumentException()
        }

    @Test
    fun partialFunctionExamples() {
        assertEquals(twice(5), 10)
        assertThrows<IllegalArgumentException> { partialTwice(105) }
    }

    /**
     * sayNumber1 은 부분함수가 아니다.
     * why? 모든 입력값에 대한 결과를 정의했으므로
     */
    private fun sayNumber1(x: Int): String = when (x) {
        1 -> "One!"
        2 -> "Two!"
        3 -> "Three!"
        else -> "Not between 1 and 3"
    }

    /**
     * sayNumber2 은 부분함수이다.
     * why? 특정 입력값에 대한 결과를 정의했으므로
     * (특정 입력값에 대해서 예외가 발생하도록 정의했으므로)
     */
    private fun sayNumber2(x: Int): String = when (x) {
        1 -> "One!"
        2 -> "Two!"
        3 -> "Three!"
        else -> throw IllegalArgumentException()
    }

    class PartialFunction<in P, out R>(
        private val condition: (P) -> Boolean,
        private val f: (P) -> R
    ) : (P) -> R {

        override fun invoke(p: P): R = when {
            condition(p) -> f(p)
            else -> throw IllegalArgumentException("$p isn't supported.")
        }

        fun isDefinedAt(p: P): Boolean = condition(p)
    }

    @Test
    fun oneTwoThreeUsingPartialFunction() {
        val condition: (Int) -> Boolean = { it in 1..3 }
        val body: (Int) -> String = {
            when (it) {
                1 -> "One!"
                2 -> "Two!"
                3 -> "Three!"
                else -> throw IllegalArgumentException()
            }
        }

        val oneTwoThree = PartialFunction(condition, body)
        assertThat(oneTwoThree((1..3).random())).isIn("One!", "Two!", "Three!")
        assertThrows<IllegalArgumentException> { oneTwoThree(Random.nextInt(4, 100)) }
    }

    @Test
    fun isEvenUsingPartialFunction() {
        val isEven = PartialFunction<Int, String>({ 0 == it % 2 }, { "$it is even" })

        assertEquals(isEven(100), "100 is even")
        assertThrows<IllegalArgumentException> {
            val oddNumber = 101
            if (!isEven.isDefinedAt(oddNumber)) {
                println("isDefinedAt($oddNumber) return false")
            }
            isEven(oddNumber)
        }
    }

    private fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean): PartialFunction<P, R> =
        PartialFunction(definedAt, this)

    @Test
    fun toPartialFunctionTest() {
        val condition: (Int) -> Boolean = { 0 == it.rem(2) }
        val body: (Int) -> String = { "$it is even" }

        val isEven = body.toPartialFunction(condition)

        assertEquals(isEven(100), "100 is even")
        assertThrows<IllegalArgumentException> {
            val oddNumber = 101
            if (!isEven.isDefinedAt(oddNumber)) {
                println("isDefinedAt($oddNumber) return false")
            }
            isEven(oddNumber)
        }
    }
}
