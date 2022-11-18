package fp.chapter08.exercises

import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0812LiftFunList {
    private fun <A, B, R> liftA2(binaryFunction: (A, B) -> R): (FunList<A>, FunList<B>) -> FunList<R> =
        { f1: FunList<A>, f2: FunList<B> -> FunList.pure(binaryFunction.curried()) apply f1 apply f2 }

    @Test
    fun run() {
        val lifted = liftA2 { x: Int, y: Int -> x + y }
        assertEquals(lifted(Cons(1, Nil), Cons(2, Nil)), Cons(3, Nil))

        val lifted2 = liftA2 { x: String, y: String -> x + y }
        assertEquals(lifted2(Cons("Hello, ", Nil), Cons("Kotlin", Nil)), Cons("Hello, Kotlin", Nil))

        val lifted3 = liftA2 { x: Int, y: String -> x.toString() + y }
        assertEquals(lifted3(Cons(10, Nil), Cons("Kotlin", Nil)), Cons("10Kotlin", Nil))

        assertEquals(
            lifted3(Cons(10, Cons(20, Nil)), Cons("Hello, ", Cons("Kotlin", Nil))),
            Cons("10Hello, ", Cons("10Kotlin", Cons("20Hello, ", Cons("20Kotlin", Nil)))),
        )
    }
}
