package fp.chapter11.exercises

import fp.chapter11.logging.WriterMonad
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P1101WriteMonadWithMonadLaw {
    @Test
    fun run() {
        val f: (Int) -> WriterMonad<Int> = { x: Int -> WriterMonad.pure(x) }
        val value = 3

        assertEquals(WriterMonad.pure(value) flatMap f, f(value))

        val m = WriterMonad.pure(value)
        assertEquals(m flatMap { WriterMonad.pure(it) }, m)

        val g = { _: Int -> WriterMonad.pure(value) }
        assertEquals((m flatMap f) flatMap g, m flatMap { x: Int -> f(x) flatMap g })
    }
}
