package fp.chapter03

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HowToMakeRecursion {
    private fun func(n: Int): Int = when {
        n < 0 -> 0
        else -> n + func(n - 1)
    }

    private fun helloFunc() {
        println("Hello")
        helloFunc()
    }

    private fun helloFunc(n: Int) {
        when {
            n < 0 -> return
            else -> {
                println("Hello")
                helloFunc(n - 1)
            }
        }
    }

    @Test
    fun run() {
        assertEquals(func(5), 15)
    }
}
