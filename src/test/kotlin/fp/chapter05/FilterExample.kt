package fp.chapter05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FilterExample {
    private fun imperativeFilter(numList: List<Int>): List<Int> {
        val newList = mutableListOf<Int>()
        for (num in numList) {
            if (num % 2 == 0) {
                newList.add(num)
            }
        }
        return newList
    }

    private fun functionalFilter(numList: List<Int>): List<Int> =
        numList.filter { it % 2 == 0 }

    @Test
    fun run() {
        assertEquals(imperativeFilter(listOf(1, 2, 3, 4, 5, 6, 7, 8)), listOf(2, 4, 6, 8))
        assertEquals(functionalFilter(listOf(1, 2, 3, 4, 5, 6, 7, 8)), listOf(2, 4, 6, 8))
    }
}
