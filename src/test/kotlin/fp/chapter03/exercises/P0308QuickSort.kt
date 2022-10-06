package fp.chapter03.exercises

import fp.head
import fp.tail
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0308QuickSort {
    private fun quickSort(list: List<Int>): List<Int> = when {
        list.isEmpty() -> list
        else -> {
            val pivot = list.head()
            val (small, bigger) = list.tail().partition { it < pivot }
            quickSort(small) + listOf(pivot) + quickSort(bigger)
        }
    }

    @Test
    fun run() {
        assertEquals(quickSort(listOf(1, 3, 5, 4, 2)), listOf(1, 2, 3, 4, 5))
    }
}
