package fp.chapter09.exercises

import fp.chapter05.FunList
import fp.chapter05.concat
import fp.chapter05.funListOf
import fp.chapter09.Monoid
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FunListMonoid<T> : Monoid<FunList<T>> {
    override fun mempty(): FunList<T> = FunList.Nil

    override fun mappend(m1: FunList<T>, m2: FunList<T>): FunList<T> = when (m1) {
        is FunList.Nil -> m2
        is FunList.Cons -> m1 concat m2
    }
}

class P0907FunListMonoid {
    @Test
    fun run() {
        val x = funListOf(1, 2, 3)
        val y = funListOf(4, 5, 6)

        FunListMonoid<Int>().run {
            assertEquals(mappend(x, y), funListOf(1, 2, 3, 4, 5, 6))
            assertEquals(mappend(x, FunList.Nil), x)
            assertEquals(mappend(FunList.Nil, x), x)
            assertEquals(mappend(y, FunList.Nil), y)
            assertEquals(mappend(FunList.Nil, y), y)
        }
    }
}
