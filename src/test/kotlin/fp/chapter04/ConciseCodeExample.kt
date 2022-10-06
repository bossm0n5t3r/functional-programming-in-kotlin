package fp.chapter04

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ConciseCodeExample {
    private val ints: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    @Test
    fun imperativeProgrammingExample() {
        val over10Values: ArrayList<Int> = ArrayList()

        for (element in ints) {
            val twiceInt = element * 2
            if (twiceInt > 10) {
                over10Values.add(twiceInt)
            }
        }

        assertEquals(over10Values, listOf(12, 14, 16, 18, 20))
    }

    @Test
    fun higherFunctionsExample() {
        val over10Values = ints
            .map { value -> value * 2 }
            .filter { value -> value > 10 }

        assertEquals(over10Values, listOf(12, 14, 16, 18, 20))
    }

    @Test
    fun usingKotlinItExample() {
        val over10Values = ints
            .map { it * 2 }
            .filter { it > 10 }

        assertEquals(over10Values, listOf(12, 14, 16, 18, 20))
    }
}
