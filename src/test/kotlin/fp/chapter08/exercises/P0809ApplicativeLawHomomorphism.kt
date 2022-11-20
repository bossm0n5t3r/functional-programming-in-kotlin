package fp.chapter08.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0809ApplicativeLawHomomorphism {
    @Test
    fun run() {
        val function: (Int) -> Int = { x: Int -> x * 10 }
        val value: Int = 1

        val left = FunList.pure(function) apply FunList.pure(value)
        val right = FunList.pure(function(value))

        assertEquals(left, right)
    }
}
