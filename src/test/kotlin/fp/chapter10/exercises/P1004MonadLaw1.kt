package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P1004MonadLaw1 {
    @Test
    fun run() {
        val f: (Int) -> FunList<Int> = { x: Int -> Cons(x * 3, Nil) }
        val value = 3

        assertEquals(FunList.pure(value) flatMap f, f(value))
    }
}
