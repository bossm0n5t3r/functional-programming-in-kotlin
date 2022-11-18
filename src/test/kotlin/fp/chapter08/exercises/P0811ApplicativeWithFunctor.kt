package fp.chapter08.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0811ApplicativeWithFunctor {
    @Test
    fun run() {
        val function: (Int) -> Int = { x: Int -> x * 10 }
        val value = 10

        val applicative = FunList.pure(function) apply FunList.pure(value)
        val functor = FunList.pure(value).fmap(function)

        assertEquals(applicative, functor)
    }
}
