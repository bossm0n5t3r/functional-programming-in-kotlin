package fp.chapter09.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

fun <T> FunList<T>.contains(value: T): Boolean = foldMap({ it == value }, AnyMonoid())

class P0912FunListWithFoldMapContains {
    @Test
    fun run() {
        val funList = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
        val funList2 = Cons('a', Cons('b', Cons('c', Cons('d', Cons('e', Nil)))))

        assertTrue(funList.contains(1))
        assertFalse(funList.contains(6))

        assertTrue(funList2.contains('c'))
        assertFalse(funList2.contains('x'))
    }
}
