package fp.chapter08

import fp.chapter05.FunList
import fp.chapter05.funListOf
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <A, B, R> liftA2(binaryFunction: (A, B) -> R) = { f1: Maybe<A>, f2: Maybe<B> ->
    Maybe.pure(binaryFunction.curried()) apply f1 apply f2
}

class LiftA2 {
    @Test
    fun run() {
        assertEquals(Just(10).fmap { listOf(it) }, Just(listOf(10)))

        assertEquals(
            Maybe.pure({ x: Int, y: FunList<Int> -> FunList.Cons(x, y) }.curried())
                apply Just(3)
                apply Just(funListOf(10)),
            Just(FunList.Cons(3, FunList.Cons(10, FunList.Nil))),
        )

        val lifted =
            liftA2 { x: Int, y: FunList<Int> -> FunList.Cons(x, y) }

        assertEquals(
            lifted(Just(3), Just(funListOf(10))),
            Just(FunList.Cons(3, FunList.Cons(10, FunList.Nil))),
        )
    }
}
