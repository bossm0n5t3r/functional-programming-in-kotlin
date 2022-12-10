package fp.chapter10.exercises

import fp.chapter10.Monad
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class FunList<out A> : Monad<A> {

    companion object {
        infix fun <V> pure(value: V): FunList<V> = Cons(0, Nil).pure(value)
    }

    override infix fun <V> pure(value: V): FunList<V> = when (this) {
        is Cons -> Cons(value, Nil)
        is Nil -> Nil
    }

    override infix fun <B> flatMap(f: (A) -> Monad<B>): FunList<B> = when (this) {
        is Cons -> try {
            f(head) as FunList<B> mappend tail.flatMap(f)
        } catch (e: ClassCastException) {
            Nil
        }

        is Nil -> Nil
    }

    infix fun <A> FunList<A>.mappend(other: FunList<A>): FunList<A> = when (this) {
        is Cons -> when (other) {
            is Cons -> Cons(this.head, this.tail.mappend(other))
            is Nil -> this
        }

        is Nil -> other
    }

    infix fun <B> leadTo(m: FunList<B>): FunList<B> = flatMap { m }
}

infix fun <A, B> FunList<(A) -> B>.apply(f: FunList<A>): FunList<B> = when (this) {
    is Cons -> f.fmap(head) as FunList<B> mappend tail.apply(f)
    is Nil -> Nil
}

data class Cons<out A>(val head: A, val tail: FunList<A>) : FunList<A>()

object Nil : FunList<Nothing>()

class P1001FunListMonad {
    @Test
    fun run() {
        val funList = Cons(1, Cons(2, Cons(3, Nil)))
        val result = funList.flatMap { Cons(it, Cons(it * 2, Cons(it * 3, Nil))) }

        assertEquals(result, Cons(1, Cons(2, Cons(3, Cons(2, Cons(4, Cons(6, Cons(3, Cons(6, Cons(9, Nil))))))))))

        val nilList = Nil
        val nilResult = nilList.flatMap { x: Int -> Cons(x, Cons(x * 2, Cons(x * 3, Nil))) }

        assertEquals(nilResult, Nil)
    }
}
