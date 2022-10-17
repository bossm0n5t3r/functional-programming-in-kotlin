package fp.chapter05

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PracticalExamples {
    private tailrec fun <T> FunList<T>.toString1(acc: String = ""): String = when (this) {
        FunList.Nil -> "[$acc]"
        is FunList.Cons -> if (acc.isEmpty()) {
            tail.toString1("$head")
        } else {
            tail.toString1("$acc, $head")
        }
    }

    @Test
    fun toString1Test() {
        assertEquals(
            funListOf(1, 2, 3, 4).toString1(),
            "[1, 2, 3, 4]"
        )
    }

    tailrec fun <T> FunList<T>.toString2(acc: String = ""): String = when (this) {
        FunList.Nil -> "[${acc.drop(2)}]"
        is FunList.Cons -> tail.toString2("$acc, $head")
    }

    @Test
    fun toString2Test() {
        assertEquals(
            funListOf(1, 2, 3, 4).toString2(),
            "[1, 2, 3, 4]"
        )
    }

    fun <T> printFunList(list: FunList<T>) = println(list.toStringByFoldLeft())

    private fun <T> FunList<T>.toStringByFoldLeft(): String =
        "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"

    @Test
    fun toStringByFoldLeftTest() {
        assertEquals(
            funListOf(1, 2, 3, 4).toStringByFoldLeft(),
            "[1, 2, 3, 4]"
        )
    }
}
