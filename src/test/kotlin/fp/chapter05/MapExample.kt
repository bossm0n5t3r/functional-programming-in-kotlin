package fp.chapter05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MapExample {
    private fun imperativeMap(numList: List<Int>): List<Int> {
        val newList = mutableListOf<Int>()
        for (num in numList) {
            newList.add(num + 2)
        }

        return newList
    }

    private fun functionalMap(numList: List<Int>): List<Int> {
        return numList.map { it + 2 }
    }

    @Test
    fun run() {
        assertEquals(imperativeMap(listOf(1, 2, 3, 4, 5, 6, 7, 8)), listOf(3, 4, 5, 6, 7, 8, 9, 10))
        assertEquals(functionalMap(listOf(1, 2, 3, 4, 5, 6, 7, 8)), listOf(3, 4, 5, 6, 7, 8, 9, 10))
    }
}
