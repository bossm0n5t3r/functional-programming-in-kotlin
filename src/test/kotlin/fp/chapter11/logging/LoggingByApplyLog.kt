package fp.chapter11.logging

import fp.chapter10.exercises.FunStream
import fp.chapter10.exercises.flatMap
import fp.chapter10.exercises.fmap
import fp.chapter10.exercises.funStreamOf
import fp.chapter10.exercises.mappend
import fp.chapter10.exercises.printFunStream
import org.junit.jupiter.api.Test

class LoggingByApplyLog {
    private fun functionalSolution3(list: FunStream<Int>) = list
        .fmap { Pair(addFive(it), funStreamOf("$it + 5")) }
        .fmap { it.applyLog { x -> Pair(square(x), funStreamOf("$x * $x")) } }
        .fmap { it.applyLog { x -> Pair(isGreaterThan50(x), funStreamOf("$x > 50")) } }

    private fun addFive(it: Int) = it + 5

    private fun square(it: Int) = it * it

    private fun isGreaterThan50(it: Int) = it > 50

    private fun <T, R> Pair<T, FunStream<String>>.applyLog(
        f: (T) -> Pair<R, FunStream<String>>
    ): Pair<R, FunStream<String>> {
        val applied = f(this.first)
        return Pair(applied.first, this.second mappend applied.second)
    }

    @Test
    fun run() {
        val result = functionalSolution3(funStreamOf(1, 2, 3))

        printFunStream(result.fmap { it.first }) // [false, false, true]
        printFunStream(
            result.flatMap { it.second } as FunStream<*>
        ) // [1 + 5, 6 * 6, 36 > 50, 2 + 5, 7 * 7, 49 > 50, 3 + 5, 8 * 8, 64 > 50]
    }
}
