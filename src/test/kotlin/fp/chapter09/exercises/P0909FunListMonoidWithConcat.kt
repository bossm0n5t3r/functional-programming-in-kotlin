package fp.chapter09.exercises

import fp.chapter05.FunList
import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0909FunListMonoidWithConcat {
    @Test
    fun run() {
        val list1 = funListOf(1, 2)
        val list2 = funListOf(3, 4)

        FunListMonoid<Int>().run {
            assertEquals(mappend(list1, list2), funListOf(1, 2, 3, 4))
            assertEquals(mappend(list1, FunList.Nil), list1)
            assertEquals(mappend(FunList.Nil, list2), list2)
        }
    }
}
