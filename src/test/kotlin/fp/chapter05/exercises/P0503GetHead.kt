package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.FunList.Cons
import fp.chapter05.FunList.Nil
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.NoSuchElementException
import kotlin.test.assertEquals

class P0503GetHead {
    private fun <T> FunList<T>.getHead(): T = when (this) {
        Nil -> throw NoSuchElementException()
        is Cons -> head
    }

    @Test
    fun run() {
        val intList = Cons(1, Cons(2, Nil))
        assertEquals(intList.getHead(), 1)

        val emptyList = Nil
        assertThrows<NoSuchElementException> { emptyList.getHead() }
    }
}
