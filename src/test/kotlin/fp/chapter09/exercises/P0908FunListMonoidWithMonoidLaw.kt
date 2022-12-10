package fp.chapter09.exercises

import fp.chapter05.FunList
import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0908FunListMonoidWithMonoidLaw {
    @Test
    fun run() {
        val x = funListOf(1, 2, 3)
        val y = funListOf(4, 5, 6)
        val z = funListOf(7, 8, 9)

        FunListMonoid<Int>().run {
            assertEquals(mappend(x, y), funListOf(1, 2, 3, 4, 5, 6))
            assertEquals(mappend(x, FunList.Nil), x)
            assertEquals(mappend(FunList.Nil, x), x)
            assertEquals(mappend(y, FunList.Nil), y)
            assertEquals(mappend(FunList.Nil, y), y)
            assertEquals(mappend(mappend(x, y), z), mappend(x, mappend(y, z)))
        }
    }
}
