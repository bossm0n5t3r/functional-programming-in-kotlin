package fp.chapter08

import fp.chapter05.FunList
import fp.chapter05.foldLeft
import fp.chapter05.foldRight
import fp.chapter05.funListOf
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T> cons() = { x: T, xs: FunList<T> -> FunList.Cons(x, xs) }

fun <T> sequenceA(maybeList: FunList<Maybe<T>>): Maybe<FunList<T>> = when (maybeList) {
    is FunList.Nil -> Just(funListOf())
    is FunList.Cons -> Maybe.pure(cons<T>().curried()) apply maybeList.head apply sequenceA(maybeList.tail)
}

fun <T> sequenceAByFoldRight(maybeList: FunList<Maybe<T>>): Maybe<FunList<T>> =
    maybeList.foldRight(Maybe.pure(funListOf()), liftA2(cons()))

class SequenceA {
    private fun <T> FunList<T>.toStringByFoldLeft(): String =
        "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"

    @Test
    fun run() {
        when (val result = sequenceA(funListOf(Just(10), Just(20)))) {
            is Nothing -> Nothing
            is Just -> assertEquals(result.value.toStringByFoldLeft(), "[10, 20]")
        }

        when (val result = sequenceAByFoldRight(funListOf(Just(10), Just(20)))) {
            is Nothing -> Nothing
            is Just -> assertEquals(result.value.toStringByFoldLeft(), "[10, 20]")
        }
    }
}
