package fp.chapter05

import fp.chapter05.exercises.filter
import fp.chapter05.exercises.map
import org.junit.jupiter.api.Test

class FunListVsFunStream {
    private fun funListWay(intList: FunList<Int>): Int = intList
        .map { n -> n * n }
        .filter { n -> n < 1000000 }
        .map { n -> n - 2 }
        .filter { n -> n < 1000 }
        .map { n -> n * 10 }
        .getHead()

    private fun funStreamWay(intList: FunStream<Int>): Int = intList
        .map { n -> n * n }
        .filter { n -> n < 1000000 }
        .map { n -> n - 2 }
        .filter { n -> n < 1000 }
        .map { n -> n * 10 }
        .getHead()

    @Test
    fun run() {
        val bigIntList = (1..1000000).toFunList()
        val bigIntListStart = System.currentTimeMillis()
        funListWay(bigIntList)
        println("${System.currentTimeMillis() - bigIntListStart} ms") // 177 ms

        val bigIntStream = (1..1000000).toFunStream()
        val bigIntStreamStart = System.currentTimeMillis()
        funStreamWay(bigIntStream)
        println("${System.currentTimeMillis() - bigIntStreamStart} ms") // 1 ms
    }
}
