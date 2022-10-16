package fp.chapter05.exercises

import org.junit.jupiter.api.Test

class P0516PerformanceTest {
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

    private fun realFunctionalWay(intList: List<Int>): Int =
        intList.asSequence()
            .map { n -> n * n }
            .first { n -> n < 10 }

    @Test
    fun runWithSpeedTest() {
        val bigIntList = (10000000 downTo 1).toList()

        val imperativeWayStart = System.currentTimeMillis()
        imperativeWay(bigIntList)
        println("${System.currentTimeMillis() - imperativeWayStart} ms") // 0 ms

        val functionalWayStart = System.currentTimeMillis()
        functionalWay(bigIntList)
        println("${System.currentTimeMillis() - functionalWayStart} ms") // 176 ms

        val realFunctionalWayStart = System.currentTimeMillis()
        realFunctionalWay(bigIntList)
        println("${System.currentTimeMillis() - realFunctionalWayStart} ms") // 2 ms
    }
}
