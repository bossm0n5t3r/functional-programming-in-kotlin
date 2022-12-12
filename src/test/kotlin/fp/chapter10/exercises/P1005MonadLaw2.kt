package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P1005MonadLaw2 {
    @Test
    fun run() {
        val m = Cons(3, Nil)

        assertEquals(m flatMap { FunList.pure(it) }, m)
    }
}
