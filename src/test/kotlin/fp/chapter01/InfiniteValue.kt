package fp.chapter01

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class InfiniteValue {
    @Test
    fun run() {
        val infiniteValue = generateSequence(0) { it + 5 }
        assertEquals(infiniteValue.take(5).map { it }.toList(), listOf(0, 5, 10, 15, 20))
    }
}
