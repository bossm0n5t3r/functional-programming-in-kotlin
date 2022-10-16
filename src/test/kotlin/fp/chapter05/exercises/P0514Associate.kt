package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.foldLeft
import fp.chapter05.foldRight
import fp.chapter05.funListOf
import fp.chapter05.getHead
import fp.chapter05.getTail
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0514Associate {
    private fun <T, R> FunList<T>.associate(f: (T) -> Pair<T, R>): Map<T, R> = when (this) {
        is FunList.Nil -> emptyMap()
        is FunList.Cons -> mapOf(f(getHead())) + getTail().associate(f)
    }

    private fun <T, R> FunList<T>.associateUsingFoldRight(f: (T) -> Pair<T, R>): Map<T, R> =
        foldRight(mapOf()) { value, acc -> acc + f(value) }

    private fun <T, R> FunList<T>.associateUsingFoldLeft(f: (T) -> Pair<T, R>): Map<T, R> =
        foldLeft(mapOf()) { acc, value -> acc + f(value) }

    @Test
    fun run() {
        assertEquals(
            funListOf(1, 2, 3, 4, 5).associate { it to it * 10 },
            mapOf(1 to 10, 2 to 20, 3 to 30, 4 to 40, 5 to 50)
        )

        assertEquals(
            funListOf(1, 2, 3, 4, 5).associateUsingFoldRight { it to it * 10 },
            mapOf(1 to 10, 2 to 20, 3 to 30, 4 to 40, 5 to 50)
        )

        assertEquals(
            funListOf(1, 2, 3, 4, 5).associateUsingFoldLeft { it to it * 10 },
            mapOf(1 to 10, 2 to 20, 3 to 30, 4 to 40, 5 to 50)
        )
    }
}
