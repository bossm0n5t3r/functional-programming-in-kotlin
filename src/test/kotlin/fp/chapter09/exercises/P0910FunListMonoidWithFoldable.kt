package fp.chapter09.exercises

import fp.chapter09.Foldable
import fp.chapter09.SumMonoid
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * TODO : SEE THIS LATER
 *
 * - HARD TO UNDERSTAND (05.12.22)
 */
sealed class FunList<out T> : Foldable<T>

object Nil : FunList<Nothing>() {
    override fun <B> foldLeft(acc: B, f: (B, Nothing) -> B): B = acc
}

data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>() {
    override fun <B> foldLeft(acc: B, f: (B, T) -> B): B = tail.foldLeft(f(acc, head), f)
}

class P0910FunListMonoidWithFoldable {
    @Test
    fun run() {
        val list1 = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))

        assertEquals(list1.foldLeft(0) { acc, value -> acc + value }, 10)
        assertEquals(list1.foldMap({ x -> x + 1 }, SumMonoid()), 14)
        assertEquals(list1.foldMap({ x -> x * 2 }, SumMonoid()), 20)
    }
}
