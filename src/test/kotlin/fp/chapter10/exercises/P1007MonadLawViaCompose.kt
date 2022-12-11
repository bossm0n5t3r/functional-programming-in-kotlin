package fp.chapter10.exercises

import fp.chapter10.Monad
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

infix fun <F, G, R> ((F) -> Monad<R>).compose(g: (G) -> Monad<F>): (G) -> Monad<R> {
    return { gInput: G -> g(gInput) flatMap this }
}

class P1007MonadLawViaCompose {
    @Test
    fun run() {
        val f = { x: Int -> Cons(x * 10, Nil) }
        val g = { x: Int -> Cons(x + 1, Nil) }
        val h = { x: Int -> Cons(x - 5, Nil) }
        val pure = { x: Int -> FunList.pure(x) }

        assertEquals((pure compose f)(10), f(10))
        assertEquals((f compose pure)(10), f(10))
        assertEquals(((f compose g) compose h)(10), (f compose (g compose h))(10))
    }
}
