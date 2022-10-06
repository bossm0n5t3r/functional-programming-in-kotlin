package fp.chapter04.exercises

import fp.head
import fp.tail
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0407TakeWhile {
    private tailrec fun <P> takeWhile(
        predicate: (P) -> Boolean,
        list: List<P>,
        acc: List<P> = emptyList(),
    ): List<P> = when {
        list.isEmpty() || !predicate(list.head()) -> acc
        else -> takeWhile(predicate, list.tail(), acc + list.head())
    }

    @Test
    fun run() {
        assertEquals(takeWhile({ p -> p < 3 }, listOf(1, 2, 3, 4, 5)), listOf(1, 2))
        assertEquals(takeWhile({ p -> ' ' != p }, "hello world".toList()), listOf('h', 'e', 'l', 'l', 'o'))
    }
}
