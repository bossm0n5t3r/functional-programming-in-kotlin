package fp.chapter06

import fp.chapter05.FunList
import fp.chapter05.FunList.Cons
import fp.chapter05.FunList.Nil
import fp.chapter05.addHead
import fp.chapter05.foldLeft
import org.junit.jupiter.api.Test

class RecursiveDataStructure {
    private fun <T> reverse(list: FunList<T>, acc: FunList<T>): FunList<T> = when (list) {
        Nil -> acc
        is Cons -> reverse(list.tail, acc.addHead(list.head))
    }

    private fun <T> printFunList(list: FunList<T>) = println(list.toStringByFoldLeft())

    private fun <T> FunList<T>.toStringByFoldLeft(): String =
        "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"

    @Test
    fun run() {
        val reversed = reverse(Cons(1, Cons(2, Cons(3, Nil))), Nil)
        printFunList(reversed)
    }
}
