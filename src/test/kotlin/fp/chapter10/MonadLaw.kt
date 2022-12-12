package fp.chapter10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

private infix fun <F, G, R> ((F) -> Monad<R>).compose(g: (G) -> Monad<F>): (G) -> Monad<R> {
    return { gInput: G -> g(gInput) flatMap this }
}

class MonadLaw {
    private val x = 10
    private val f = { a: Int -> Just(a * 2) }
    private val g = { a: Int -> Just(a + 1) }
    private val h = { a: Int -> Just(a * 10) }
    private val pure = { a: Int -> Just(a) }
    private val m = Just(10)

    /**
     * Left Identity
     *
     * pure(x) flatMap f = f(x)
     */
    @Test
    fun leftIdentity() {
        assertEquals(pure(x) flatMap f, f(x))
    }

    /**
     * Right Identity
     *
     * m flatMap pure = m
     */
    @Test
    fun rightIdentity() {
        assertEquals(m flatMap pure, m)
    }

    /**
     * Associativity Law
     *
     * (m flatMap f) flatMap g = m flatMap { x -> f(x) flatMap g }
     */
    @Test
    fun associativityLaw() {
        assertEquals((m flatMap f) flatMap g, m flatMap { a -> f(a) flatMap g })
    }

    /**
     * identity compose f = f
     *
     * f compose identity = f
     * (f compose g) compose h = f compose (g compose h)
     */
    @Test
    fun identity() {
        assertEquals((pure compose f)(10), f(10))
        assertEquals((f compose pure)(10), f(10))
        assertEquals(((f compose g) compose h)(10), (f compose (g compose h))(10))
    }
}
