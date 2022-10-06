package fp.chapter04

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HigherOrderFunctions {
    private fun higherOrderFunction1(func: () -> String) = func()

    private fun higherOrderFunction2(): () -> String {
        return { "Hello, Kotlin" }
    }

    @Test
    fun run() {
        assertEquals(higherOrderFunction1 { "Hello, Kotlin" }, "Hello, Kotlin")
        assertEquals(higherOrderFunction2()(), "Hello, Kotlin")
    }
}
