package fp.chapter10.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P1002FunListMonad {
    @Test
    fun run() {
        val funList = Cons(1, Cons(2, Cons(3, Nil)))
        val funlist2 = Cons({ x: Int -> x + 1 }, Cons({ x: Int -> x * 10 }, Nil))

        val fmapResult = funList.fmap { it * 3 }
        val applyResult = funlist2 apply Cons(1, Cons(2, Nil))
        val flatmapResult = funList.flatMap { Cons(it, Cons(it * 2, Cons(it * 3, Nil))) }
        val leadToResult = funList.leadTo(Cons('a', Cons('b', Cons('c', Nil))))

        assertEquals(Cons(1, Nil), FunList.pure(1))
        assertEquals(fmapResult, Cons(3, Cons(6, Cons(9, Nil))))
        assertEquals(applyResult, Cons(2, Cons(3, Cons(10, Cons(20, Nil)))))
        assertEquals(
            flatmapResult,
            Cons(1, Cons(2, Cons(3, Cons(2, Cons(4, Cons(6, Cons(3, Cons(6, Cons(9, Nil)))))))))
        )
        assertEquals(
            leadToResult,
            Cons('a', Cons('b', Cons('c', Cons('a', Cons('b', Cons('c', Cons('a', Cons('b', Cons('c', Nil)))))))))
        )

        val nilList = Nil
        val nilFmapResult = nilList.fmap { x: Int -> x * 3 }
        val nilApplyResult = nilList as FunList<(Int) -> Int> apply Cons(1, Cons(2, Nil))
        val nilFlatmapResult = nilList.flatMap { x: Int -> Cons(x, Cons(x * 2, Cons(x * 3, Nil))) }
        val nilLeadToResult = nilList.leadTo(Cons('a', Cons('b', Cons('c', Nil))))

        assertEquals(Nil.pure(1), Nil)
        assertEquals(nilFmapResult, Nil)
        assertEquals(nilApplyResult, Nil)
        assertEquals(nilFlatmapResult, Nil)
        assertEquals(nilLeadToResult, Nil)
    }
}
