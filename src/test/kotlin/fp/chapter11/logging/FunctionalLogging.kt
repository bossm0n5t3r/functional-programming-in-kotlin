package fp.chapter11.logging

import fp.chapter10.exercises.FunStream
import fp.chapter10.exercises.fmap
import fp.chapter10.exercises.funStreamOf
import fp.chapter10.exercises.printFunStream
import org.junit.jupiter.api.Test

class FunctionalLogging {
    private fun functionalFunction(list: FunStream<Int>) = list
        .fmap { it + 5 }
        .fmap { it * it }
        .fmap { it > 50 }

    private fun functionalSolution1(list: FunStream<Int>) = list
        .fmap {
            println("$it + 5")
            it + 5
        }
        .fmap {
            println("$it * $it")
            it * it
        }
        .fmap {
            println("$it > 50")
            it > 50
        }

    private fun functionalSolution2(list: FunStream<Int>) = list
        .fmap { addFive(it) withLog "$it + 5" }
        .fmap { square(it) withLog "$it * $it" }
        .fmap { isGreaterThan50(it) withLog "$it > 50" }

    private fun addFive(it: Int) = it + 5

    private fun square(it: Int) = it * it

    private fun isGreaterThan50(it: Int) = it > 50

    private infix fun <T> T.withLog(log: String): T {
        println(log)
        return this
    }

    @Test
    fun run() {
        functionalFunction(funStreamOf(1, 2, 3))

        println("**** Log in higher order function ****")
        val result1 = functionalSolution1(funStreamOf(1, 2, 3))
        println(funStreamOf(result1))

        println("**** Log in extension function ****")
        val result2 = functionalSolution2(funStreamOf(1, 2, 3))
        println(printFunStream(result2))
    }
}
