package fp.chapter07.exercises

import fp.compose
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T> identity(value: T): T = value

class P0702FunctorLawFunList {
    @Test
    fun run() {
        val funList: FunList<Int> = Cons(1, Cons(2, Cons(3, Nil)))

        // functor first law
        assertEquals(funList, funList.fmap { identity(it) })

        // functor second law
        val add5: (Int) -> Int = { it + 5 }
        val twice: (Int) -> Int = { it * 2 }

        val left = funList.fmap(add5 compose twice)
        val right = funList.fmap(twice).fmap(add5)

        assertEquals(left, right)
    }
}
