package fp.chapter10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class FunList<out T> {
    companion object
}

object Nil : FunList<kotlin.Nothing>() {
    override fun toString(): String = "[]"
}

data class Cons<out T>(val head: T, val tail: FunList<T>) : FunList<T>() {
    override fun toString(): String = "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"
}

fun <T> funListOf(vararg elements: T): FunList<T> = elements.toFunList()

private fun <T> Array<out T>.toFunList(): FunList<T> = when {
    this.isEmpty() -> Nil
    else -> Cons(this[0], this.copyOfRange(1, this.size).toFunList())
}

fun <T> FunList<T>.mempty() = Nil

infix fun <T> FunList<T>.mappend(other: FunList<T>): FunList<T> = when (this) {
    is Nil -> other
    is Cons -> Cons(head, tail.mappend(other))
}

fun <T> FunList<FunList<T>>.flatten(): FunList<T> = foldRight(mempty()) { t, r: FunList<T> -> t mappend r }

fun <T> FunList.Companion.pure(value: T): FunList<T> = Cons(value, Nil)

infix fun <T, R> FunList<(T) -> R>.apply(f: FunList<T>): FunList<R> = when (this) {
    is Nil -> Nil
    is Cons -> f.fmap(head) mappend tail.apply(f)
}

infix fun <T, R> FunList<T>._apply(f: FunList<(T) -> R>): FunList<R> = when (this) {
    is Nil -> Nil
    is Cons -> f.fmap { it(head) } mappend tail._apply(f)
}

// infix fun <T, R> FunList<T>.flatMap(f: (T) -> FunList<R>): FunList<R> = when (this) {
//    is Nil -> Nil
//    is Cons -> f(head) mappend tail.flatMap(f)
// }

infix fun <T, R> FunList<T>.flatMap(f: (T) -> FunList<R>): FunList<R> = fmap(f).flatten()

infix fun <T, R> FunList<T>.fmap(f: (T) -> R): FunList<R> = when (this) {
    is Nil -> Nil
    is Cons -> Cons(f(head), tail.fmap(f))
}

// infix fun <T, R> FunList<T>.fmap(f: (T) -> R): FunList<R> = flatMap { x -> Cons(f(x), Nil) }

fun <T, R> FunList<T>.foldRight(acc: R, f: (T, R) -> R): R = when (this) {
    is Nil -> acc
    is Cons -> f(head, tail.foldRight(acc, f))
}

tailrec fun <T, R> FunList<T>.foldLeft(acc: R, f: (R, T) -> R): R = when (this) {
    is Nil -> acc
    is Cons -> tail.foldLeft(f(acc, head), f)
}

tailrec fun <T> FunList<T>.contains(element: T): Boolean = when (this) {
    is Nil -> false
    is Cons -> if (element == head) true else tail.contains(element)
}

fun <T> FunList<T>.distinct(): FunList<T> =
    foldLeft(Nil as FunList<T>) { acc, x -> if (acc.contains(x)) acc else Cons(x, acc) }

tailrec fun <T> FunList<T>.reverse(acc: FunList<T> = Nil): FunList<T> = when (this) {
    is Nil -> acc
    is Cons -> tail.reverse(Cons(head, acc))
}

fun <T> FunList<T>.filter(acc: FunList<T> = Nil, f: (T) -> Boolean): FunList<T> = when (this) {
    Nil -> acc.reverse()
    is Cons -> if (f(head)) {
        tail.filter(Cons(head, acc), f)
    } else {
        tail.filter(acc, f)
    }
}

fun <T> printFunList(list: FunList<T>) = println(list.toStringByFoldLeft())

private fun <T> FunList<T>.toStringByFoldLeft(): String = "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"

class ListMonad {
    @Test
    fun run() {
        val list1 = funListOf(1, 2, 3)
        val list2 = funListOf(5, 10, 15, 20)

        assertEquals(list1.mempty(), Nil)
        assertEquals(list1 mappend list2, funListOf(1, 2, 3, 5, 10, 15, 20))
        assertEquals(list1.fmap { x -> x * 2 }, funListOf(2, 4, 6))
        assertEquals(FunList.pure(10), Cons(10, Nil))

        val list3 = funListOf<(Int) -> Int>({ x -> x * 2 }, { x -> x + 1 }, { x -> x - 10 })

        assertEquals(list3 apply list1, funListOf(2, 4, 6, 2, 3, 4, -9, -8, -7))
        assertEquals(list3 apply list2, funListOf(10, 20, 30, 40, 6, 11, 16, 21, -5, 0, 5, 10))
        /**
         * TODO https://github.com/funfunStory/fp-kotlin-example/blob/master/src/main/kotlin/fp/kotlin/example/chapter10/ListMonad.kt#L15-L16
         *      실제 값과 다름. Issue 올릴 것.
         */
        assertEquals(list1 _apply list3, funListOf(2, 2, -9, 4, 3, -8, 6, 4, -7))
        assertEquals(list2 _apply list3, funListOf(10, 6, -5, 20, 11, 0, 30, 16, 5, 40, 21, 10))

        assertEquals(Nil flatMap { x -> funListOf(x) }, Nil)
        assertEquals(list1 flatMap { x -> funListOf(x, -x) }, funListOf(1, -1, 2, -2, 3, -3))
        assertEquals(funListOf(list1, list2).flatten(), funListOf(1, 2, 3, 5, 10, 15, 20))

        assertEquals(
            funListOf(1, 2)
                .flatMap { x -> funListOf(x to 'a', x to 'c') } // [(1, a), (1, c), (2, a), (2, c)]
                .fmap { x -> x.first to x.second.uppercaseChar() } // [(1, A), (1, C), (2, A), (2, C)]
                ._apply(
                    funListOf<(Pair<Int, Char>) -> Char>(
                        { x -> x.second },
                        { x -> x.second + x.first }
                    )
                ) // [A, B, C, D, A, C, C, E]
//            .foldLeft(Nil as FunList<Char>) { acc, x -> if (acc.contains(x)) acc else Cons(x, acc) }    // [E, D, C, B, A]
                .distinct() // [E, D, C, B, A]
                .reverse(), // [A, B, C, D, E]
            funListOf('A', 'B', 'C', 'D', 'E')
        )
    }
}
