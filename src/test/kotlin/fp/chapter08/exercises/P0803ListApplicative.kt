package fp.chapter08.exercises

import fp.chapter07.Functor
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class FunList<out A> : Functor<A> {
    abstract override fun <B> fmap(f: (A) -> B): FunList<B>

    companion object
}

object Nil : FunList<Nothing>() {
    override fun <B> fmap(f: (Nothing) -> B): FunList<B> = Nil
}

data class Cons<A>(val head: A, val tail: FunList<A>) : FunList<A>() {
    override fun <B> fmap(f: (A) -> B): FunList<B> = Cons(f(head), tail.fmap(f))
}

fun <A> FunList.Companion.pure(value: A): FunList<A> = Cons(value, Nil)

infix fun <A> FunList<A>.append(other: FunList<A>): FunList<A> = when (this) {
    is Cons -> Cons(head, tail append other)
    is Nil -> other
}

infix fun <A, B> FunList<(A) -> B>.apply(f: FunList<A>): FunList<B> = when (this) {
    is Cons -> f.fmap(head) append (tail apply f)
    is Nil -> Nil
}

class P0803ListApplicative {
    @Test
    fun run() {
        val funList: FunList<(Int) -> Int> = FunList.pure { x -> x * 3 }
        assertEquals(
            funList apply Cons(1, Cons(2, Cons(3, Cons(4, Nil)))),
            Cons(3, Cons(6, Cons(9, Cons(12, Nil))))
        )

        val funList2: FunList<(Int) -> Int> =
            Cons(
                { it * 3 },
                Cons(
                    { it * 10 },
                    Cons<(Int) -> Int>({ it - 2 }, Nil)
                )
            )

        assertEquals(
            funList2 apply Cons(1, Cons(2, Cons(3, Nil))),
            Cons(
                3,
                Cons(
                    6,
                    Cons(
                        9,
                        Cons(
                            10,
                            Cons(
                                20,
                                Cons(
                                    30,
                                    Cons(-1, Cons(0, Cons(1, Nil)))
                                )
                            )
                        )
                    )
                )
            )
        )

        val funList3: FunList<(Int) -> Int> = Nil
        assertEquals(
            funList3 apply Cons(1, Cons(2, Cons(3, Cons(4, Nil)))),
            Nil
        )

        val funList4: FunList<(Int) -> (Int) -> Int> =
            FunList.pure({ x: Int, y: Int -> x + y }.curried())
        assertEquals(
            funList4
                apply Cons(1, Cons(2, Cons(3, Nil)))
                apply Cons(1, Cons(2, Cons(3, Nil))),
            Cons(
                2,
                Cons(
                    3,
                    Cons(
                        4,
                        Cons(
                            3,
                            Cons(
                                4,
                                Cons(
                                    5,
                                    Cons(4, Cons(5, Cons(6, Nil)))
                                )
                            )
                        )
                    )
                )
            )
        )
    }
}
