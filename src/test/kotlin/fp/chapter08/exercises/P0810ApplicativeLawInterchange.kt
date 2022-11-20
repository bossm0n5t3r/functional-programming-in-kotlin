package fp.chapter08.exercises

import fp.chapter08.of
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0810ApplicativeLawInterchange {
    @Test
    fun run() {
        val function: (Int) -> Int = { x: Int -> x * 10 }
        val value = 10

        val left = FunList.pure(function) apply FunList.pure(value)
        val right = FunList.pure(of<Int, Int>(value)) apply FunList.pure(function)

        assertEquals(left, right)
    }
}
