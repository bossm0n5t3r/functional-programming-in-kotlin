package fp.chapter04

import fp.head
import fp.tail
import org.junit.jupiter.api.Test
import kotlin.math.max
import kotlin.test.assertEquals

class PracticalExamples {
    private tailrec fun <P1, P2, R> zipWith(
        func: (P1, P2) -> R,
        list1: List<P1>,
        list2: List<P2>,
        acc: List<R> = listOf()
    ): List<R> = when {
        list1.isEmpty() || list2.isEmpty() -> acc
        else -> {
            val zipList = acc + listOf(func(list1.head(), list2.head()))
            zipWith(func, list1.tail(), list2.tail(), zipList)
        }
    }

    @Test
    fun zipWithTestOfAdd() {
        val list1 = listOf(6, 3, 2, 1, 4)
        val list2 = listOf(7, 4, 2, 6, 3)
        val add = { p1: Int, p2: Int -> p1 + p2 }
        val result = zipWith(add, list1, list2)
        assertEquals(result, listOf(13, 7, 4, 7, 7))
    }

    @Test
    fun zipWithTestOfMax() {
        val list1 = listOf(6, 3, 2, 1, 4)
        val list2 = listOf(7, 4, 2, 6, 3)
        val max = { p1: Int, p2: Int -> max(p1, p2) }
        val result = zipWith(max, list1, list2)
        assertEquals(result, listOf(7, 4, 2, 6, 4))
    }

    @Test
    fun zipWithTestOfStrConcat() {
        val strConcat = { p1: String, p2: String -> p1 + p2 }
        val result = zipWith(strConcat, listOf("a", "b"), listOf("c", "d"))
        assertEquals(result, listOf("ac", "bd"))
    }

    @Test
    fun zipWithTestOfProduct() {
        tailrec fun replicate(n: Int, element: Int, acc: List<Int> = emptyList()): List<Int> = when {
            n <= 0 -> acc
            else -> replicate(n - 1, element, listOf(element) + acc)
        }

        val product = { p1: Int, p2: Int -> p1 * p2 }
        val result = zipWith(product, replicate(3, 5), (1..5).toList())
        assertEquals(result, listOf(5, 10, 15))
    }
}
