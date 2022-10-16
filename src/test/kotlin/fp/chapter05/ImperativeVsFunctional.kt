package fp.chapter05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ImperativeVsFunctional {
    private fun imperativeWay(intList: List<Int>): Int {
        for (value in intList) {
            val doubleValue = value * value
            if (doubleValue < 10) {
                return doubleValue
            }
        }

        throw NoSuchElementException("There is no value")
    }

    private fun functionalWay(intList: List<Int>): Int =
        intList
            .map { n -> n * n }
            .first { n -> n < 10 }

    @Test
    fun simpleRun() {
        assertEquals(imperativeWay(listOf(1, 2, 3, 4, 5)), 1)
        assertEquals(functionalWay(listOf(1, 2, 3, 4, 5)), 1)
    }

    @Test
    fun runWithSpeedTest() {
        val bigIntList = (1..10000000).toList()

        val imperativeWayStart = System.currentTimeMillis()
        imperativeWay(bigIntList)
        println("${System.currentTimeMillis() - imperativeWayStart} ms") // 0 ms

        val functionalWayStart = System.currentTimeMillis()
        functionalWay(bigIntList)
        println("${System.currentTimeMillis() - functionalWayStart} ms") // 128 ms
    }

    private fun realFunctionalWay(intList: List<Int>): Int =
        intList.asSequence()
            .map { n -> n * n }
            .first { n -> n < 10 }

    @Test
    fun runWithSequence() {
        val bigIntList = (1..10000000).toList()

        val realFunctionalWayStart = System.currentTimeMillis()
        realFunctionalWay(bigIntList)
        println("${System.currentTimeMillis() - realFunctionalWayStart} ms") // 3 ms
    }
}
