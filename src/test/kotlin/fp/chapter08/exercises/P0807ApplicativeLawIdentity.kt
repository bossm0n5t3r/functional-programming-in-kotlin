package fp.chapter08.exercises

import fp.chapter08.identity
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0807ApplicativeLawIdentity {
    @Test
    fun run() {
        assertEquals(
            FunList.pure(identity()) apply Cons(1, Cons(2, Nil)),
            Cons(1, Cons(2, Nil))
        )
    }
}
