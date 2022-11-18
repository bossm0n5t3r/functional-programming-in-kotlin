package fp.chapter08.exercises

import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0816SequenceFunList {
    private fun <T> cons() = { x: T, xs: FunList<T> -> Cons(x, xs) }

    private fun <T> sequenceAByFoldRight(listOfList: FunList<FunList<T>>): FunList<FunList<T>> =
        when (listOfList) {
            Nil -> Cons(Nil, Nil)
            is Cons ->
                FunList.pure(cons<T>().curried()) apply listOfList.head apply sequenceAByFoldRight(listOfList.tail)
        }

    @Test
    fun run() {
        val listOfList = Cons(Cons(1, Cons(2, Cons(3, Nil))), Nil)
        assertEquals(
            sequenceAByFoldRight(listOfList),
            Cons(Cons(1, Nil), Cons(Cons(2, Nil), Cons(Cons(3, Nil), Nil))),
        )
    }
}
