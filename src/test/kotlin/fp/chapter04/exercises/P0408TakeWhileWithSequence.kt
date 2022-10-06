package fp.chapter04.exercises

import fp.head
import fp.tail
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0408TakeWhileWithSequence {
    private tailrec fun <P> takeWhile(
        predicate: (P) -> Boolean,
        list: Sequence<P>,
        acc: List<P> = emptyList(),
    ): List<P> = when {
        list.none() || !predicate(list.head()) -> acc
        else -> takeWhile(predicate, list.tail(), acc + list.head())
    }

    @Test
    fun run() {
        assertEquals(
            takeWhile({ p -> p < 10 }, generateSequence(1) { it + 1 }),
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        )
    }
}
