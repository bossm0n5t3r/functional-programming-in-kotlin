package fp.chapter07

import fp.compose
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

data class UnaryFunction<in T, out R>(val g: (T) -> R) : Functor<R> {
    override fun <R2> fmap(f: (R) -> R2) = UnaryFunction { x: T -> (f compose g)(x) }

    fun invoke(input: T): R = g(input)
}

class Function {
    @Test
    fun run() {
        val f = { a: Int -> a + 1 }
        val g = { b: Int -> b * 2 }

        val fg = UnaryFunction(g).fmap(f)
        assertEquals(fg.invoke(5), 11)

        val k = { x: Int -> Just(x) }
        val kg = UnaryFunction(g).fmap(k)
        assertEquals(kg.invoke(5), Just(10))
    }
}
