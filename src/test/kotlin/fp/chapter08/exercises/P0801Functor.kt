package fp.chapter08.exercises

import fp.chapter07.exercises.Cons
import fp.chapter07.exercises.FunList
import fp.chapter07.exercises.Nil
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0801Functor {
    @Test
    fun run() {
        val product: (Int, Int) -> Int = { x: Int, y: Int -> x * y }
        val curriedProduct: (Int) -> (Int) -> Int = product.curried()
        val list = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))

        val productWithList: (Int) -> FunList<Int> = { x ->
            list.fmap(curriedProduct)
                .fmap { f ->
                    f(x)
                }
        }

        assertEquals(productWithList(5), Cons(5, Cons(10, Cons(15, Cons(20, Nil)))))
        assertEquals(productWithList(10), Cons(10, Cons(20, Cons(30, Cons(40, Nil)))))
    }
}
