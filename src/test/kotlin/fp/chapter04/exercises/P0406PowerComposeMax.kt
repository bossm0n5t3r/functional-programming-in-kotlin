package fp.chapter04.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0406PowerComposeMax {
    private infix fun <F, G, R> ((F) -> R).compose(g: (G) -> F): (G) -> R {
        return { gInput: G -> this(g(gInput)) }
    }

    private val maximum = { i: List<Int> -> i.max() }
    private val power = { i: Int -> i * i }

    @Test
    fun run() {
        val composed = power compose maximum
        assertEquals(composed(listOf(1, 2, 3, 4, 5, 6, 7)), 49)
        assertEquals(composed(listOf(10, 2, 13, 4, 0, 6, 1)), 169)
    }
}
