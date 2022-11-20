package fp.chapter08.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <A, B> FunList<(A)->B>.zipList(other: FunList<A>): FunList<B> = when {
    this is Nil -> Nil
    other is Nil -> Nil
    this is Cons && other is Cons -> Cons(this.head(other.head), this.tail.zipList(other.tail))
    else -> throw IllegalArgumentException()
}

class P0806ZipApplicative {
    @Test
    fun run() {
        val list1: FunList<(Int) -> Int> = Cons({ x: Int -> x * 5 }, Cons({ x: Int -> x + 10 }, Nil))
        val list2: FunList<Int> = Cons(10, Cons(20, Cons(30, Nil)))

        assertEquals(list1.zipList(list2), Cons(50, Cons(30, Nil)))
    }
}
