package fp.chapter07.exercises

import fp.chapter07.Functor
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

sealed class FunList<out A> : Functor<A> {
    abstract override fun <B> fmap(f: (A) -> B): FunList<B>
    abstract fun first(): A
    abstract fun size(): Int
}

object Nil : FunList<Nothing>() {

    override fun <B> fmap(f: (Nothing) -> B): FunList<B> = Nil

    override fun first(): Nothing = throw NoSuchElementException()

    override fun size(): Int = 0
}

data class Cons<A>(val head: A, val tail: FunList<A>) : FunList<A>() {
    override fun <B> fmap(f: (A) -> B): FunList<B> = Cons(f(head), tail.fmap(f))

    override fun first() = head

    override fun size() = tail.size() + 1
}

class P0701FunListWithFunctor {
    @Test
    fun run() {
        val funList: FunList<Int> = Cons(1, Cons(2, Cons(3, Nil)))

        assertEquals(
            funList.fmap { it * 3 },
            Cons(3, Cons(6, Cons(9, Nil)))
        )
        assertEquals(funList.first(), 1)
        assertEquals(funList.size(), 3)

        val funList2: FunList<Int> = Nil

        assertEquals(funList2.fmap { it * 3 }, Nil)
        assertThrows<NoSuchElementException> {
            funList2.first()
        }
        assertEquals(funList2.size(), 0)
    }
}
