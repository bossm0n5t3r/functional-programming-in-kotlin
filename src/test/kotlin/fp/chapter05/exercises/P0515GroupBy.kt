package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.addHead
import fp.chapter05.appendTail
import fp.chapter05.foldLeft
import fp.chapter05.foldRight
import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0515GroupBy {
    private fun <T, K> FunList<T>.groupBy(f: (T) -> K): Map<K, FunList<T>> =
        foldLeft(mapOf()) { acc, value ->
            val mutableAcc = acc.toMutableMap()
            val key = f(value)
            if (mutableAcc.containsKey(key)) {
                mutableAcc[key] = mutableAcc[key]?.appendTail(value) ?: error("ERROR!!!")
            } else {
                mutableAcc[key] = FunList.Cons(value, FunList.Nil)
            }
            mutableAcc
        }

    @Test
    fun run() {
        assertEquals(
            funListOf(1, 2, 3).groupBy { it },
            mapOf(1 to funListOf(1), 2 to funListOf(2), 3 to funListOf(3))
        )

        assertEquals(
            funListOf(1, 2, 3, 4, 5, 6).groupBy { it % 2 == 0 },
            mapOf(false to funListOf(1, 3, 5), true to funListOf(2, 4, 6))
        )
    }

    private fun <T, K> FunList<T>.groupByUsingFoldLeft(f: (T) -> K): Map<K, FunList<T>> =
        foldLeft(emptyMap()) { acc, value ->
            acc + (f(value) to (acc.getOrElse(f(value)) { funListOf() }.appendTail(value)))
        }

    @Test
    fun groupByUsingFoldLeftTest() {
        assertEquals(
            funListOf(1, 2, 3).groupByUsingFoldLeft { it },
            mapOf(1 to funListOf(1), 2 to funListOf(2), 3 to funListOf(3))
        )

        assertEquals(
            funListOf(1, 2, 3, 4, 5, 6).groupByUsingFoldLeft { it % 2 == 0 },
            mapOf(false to funListOf(1, 3, 5), true to funListOf(2, 4, 6))
        )
    }

    private fun <T, K> FunList<T>.groupByUsingFoldRight(f: (T) -> K): Map<K, FunList<T>> =
        foldRight(emptyMap()) { value, acc ->
            acc.plus(f(value) to (acc.getOrElse(f(value)) { funListOf() }.addHead(value)))
        }

    @Test
    fun groupByUsingFoldRightTest() {
        assertEquals(
            funListOf(1, 2, 3).groupByUsingFoldRight { it },
            mapOf(1 to funListOf(1), 2 to funListOf(2), 3 to funListOf(3))
        )

        assertEquals(
            funListOf(1, 2, 3, 4, 5, 6).groupByUsingFoldRight { it % 2 == 0 },
            mapOf(false to funListOf(1, 3, 5), true to funListOf(2, 4, 6))
        )
    }
}
