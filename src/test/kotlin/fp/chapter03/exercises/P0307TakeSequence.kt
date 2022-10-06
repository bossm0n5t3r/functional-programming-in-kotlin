package fp.chapter03.exercises

import fp.head
import fp.plus
import fp.tail
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0307TakeSequence {
    companion object {
        fun takeSequence(n: Int, sequence: Sequence<Int>): List<Int> = when {
            n <= 0 -> emptyList()
            sequence.none() -> emptyList()
            else -> listOf(sequence.head()) + takeSequence(n - 1, sequence.tail())
        }
    }

    private fun repeat(n: Int): Sequence<Int> = sequenceOf(n).plus { repeat(n) }

    @Test
    fun run() {
        assertEquals(takeSequence(5, repeat(3)), listOf(3, 3, 3, 3, 3))
    }
}
