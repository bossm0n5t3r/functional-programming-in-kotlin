package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P1006MonadLaw3 {
    @Test
    fun run() {
        val f = { x: Int -> Cons(x * 10, Nil) }
        val g = { x: Int -> Cons("value : $x", Nil) }
        val m = Cons(3, Nil)

        assertEquals((m flatMap f) flatMap g, m flatMap { x: Int -> f(x) flatMap g })
    }
}
