package fp.chapter03

import fp.chapter03.exercises.P0307TakeSequence.Companion.takeSequence
import fp.head
import fp.plus
import fp.tail
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class Recursion {
    private fun maximum(nums: List<Int>): Int = when {
        nums.isEmpty() -> error("List is empty")
        nums.size == 1 -> nums[0]
        else -> {
            val head = nums.head()
            val tail = nums.tail()
            val maxVal = maximum(tail)
            if (head > maxVal) head else maxVal
        }
    }

    @Test
    fun maximumTest() {
        assertEquals(maximum(listOf(1, 3, 2, 8, 4)), 8)
    }

    private fun reverse(str: String): String = when {
        str.isEmpty() -> ""
        else -> reverse(str.tail()) + str.head()
    }

    @Test
    fun reverseTest() {
        assertEquals(reverse("abcd"), "dcba")
    }

    private fun take(n: Int, list: List<Int>): List<Int> = when {
        n <= 0 -> emptyList()
        list.isEmpty() -> emptyList()
        else -> listOf(list.head()) + take(n - 1, list.tail())
    }

    @Test
    fun takeTest() {
        assertEquals(take(3, listOf(1, 2, 3, 4, 5)), listOf(1, 2, 3))
    }

    private fun repeatUseSequenceWithoutRecursion(n: Int): Sequence<Int> = generateSequence(n) { it }

    @Test
    fun repeatUseSequenceWithoutRecursionTest() {
        assertEquals(takeSequence(5, repeatUseSequenceWithoutRecursion(3)), listOf(3, 3, 3, 3, 3))
    }

    private fun repeatWithStackOverflowError(n: Int): Sequence<Int> = sequenceOf(n) + repeatWithStackOverflowError(n)

    @Test
    fun repeatWithStackOverflowErrorTest() {
        assertThrows<StackOverflowError> { takeSequence(5, repeatWithStackOverflowError(3)) }
    }

    private fun repeat(n: Int): Sequence<Int> = sequenceOf(n).plus { repeat(n) }

    @Test
    fun repeatTest() {
        assertEquals(takeSequence(5, repeat(3)), listOf(3, 3, 3, 3, 3))
    }

    private fun zip(list1: List<Int>, list2: List<Int>): List<Pair<Int, Int>> = when {
        list1.isEmpty() || list2.isEmpty() -> emptyList()
        else -> listOf(list1.head() to list2.head()) + zip(list1.tail(), list2.tail())
    }

    @Test
    fun zipTest() {
        assertEquals(zip(listOf(1, 3, 5), listOf(2, 4)), listOf(1 to 2, 3 to 4))
    }
}
