package fp.chapter03

import fp.head
import fp.tail
import org.junit.jupiter.api.Test
import kotlin.math.cos
import kotlin.test.assertEquals

class TailRecursion {
    /**
     * https://kotlinlang.org/docs/functions.html
     */
    private tailrec fun findFixPoint(x: Double = 1.0): Double = if (x == cos(x)) x else findFixPoint(cos(x))

    private fun findFixPoint(): Double {
        var x = 1.0
        while (true) {
            val y = cos(x)
            if (x == y) return x
            x = y
        }
    }

    /**
     * Tail Recursion Example
     * 꼬리 재귀는 모든 처리가 완료되고 마지막에 재귀 호출이 수행된다.
     */
    private fun tailRecursionExample(n: Int): Int = when (n) {
        0 -> 0
        else -> tailRecursionExample(n - 1)
    }

    /**
     * Head Recursion Example
     * 머리 재귀는 함수의 다른 처리 앞에서 재귀 호출이 수행된다.
     */
    private fun headRecursionExample(n: Int): Int = when {
        n > 0 -> headRecursionExample(n - 1)
        else -> 0
    }

    private tailrec fun maximum(items: List<Int>, acc: Int = Int.MIN_VALUE): Int = when {
        items.isEmpty() -> error("empty list")
        items.size == 1 -> acc
        else -> {
            val head = items.head()
            val maxValue = if (head > acc) head else acc
            maximum(items.tail(), maxValue)
        }
    }

    @Test
    fun maximumTest() {
        assertEquals(maximum(listOf(1, 3, 2, 8, 4)), 8)
    }

    private tailrec fun reverse(str: String, acc: String = ""): String = when {
        str.isEmpty() -> acc
        else -> {
            val reversed = str.head() + acc
            reverse(str.tail(), reversed)
        }
    }

    @Test
    fun reverseTest() {
        assertEquals(reverse("abcd"), "dcba")
    }

    private tailrec fun take(n: Int, list: List<Int>, acc: List<Int> = listOf()): List<Int> = when {
        n <= 0 -> acc
        list.isEmpty() -> acc
        else -> {
            val takeList = acc + listOf(list.head())
            take(n - 1, list.tail(), takeList)
        }
    }

    @Test
    fun takeTest() {
        assertEquals(take(3, listOf(1, 2, 3, 4, 5)), listOf(1, 2, 3))
    }

    private tailrec fun zip(
        list1: List<Int>,
        list2: List<Int>,
        acc: List<Pair<Int, Int>> = listOf()
    ): List<Pair<Int, Int>> = when {
        list1.isEmpty() || list2.isEmpty() -> acc
        else -> {
            val zipList = acc + listOf(Pair(list1.head(), list2.head()))
            zip(list1.tail(), list2.tail(), zipList)
        }
    }

    @Test
    fun zipTest() {
        assertEquals(zip(listOf(1, 3, 5), listOf(2, 4)), listOf(1 to 2, 3 to 4))
    }
}
