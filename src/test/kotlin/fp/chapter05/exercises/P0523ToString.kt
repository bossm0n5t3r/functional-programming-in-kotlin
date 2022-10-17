package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.funListOf
import fp.chapter05.getHead
import fp.chapter05.getTail
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0523ToString {
    private tailrec fun <T> FunList<T>.toString(acc: String): String = when (this) {
        is FunList.Nil -> "$acc]"
        is FunList.Cons -> getTail().toString(
            if (acc.isBlank()) {
                "[${getHead()}"
            } else {
                "$acc, ${getHead()}"
            }
        )
    }

    @Test
    fun toStringTest() {
        assertEquals(
            funListOf(1, 2, 3, 4, 5).toString(""),
            "[1, 2, 3, 4, 5]"
        )
    }

    private tailrec fun <T> FunList<T>.toStringInAnotherWay(acc: String): String = when (this) {
        is FunList.Nil -> "[${acc.drop(2)}]"
        is FunList.Cons -> getTail().toStringInAnotherWay("$acc, ${getHead()}")
    }

    @Test
    fun toStringInAnotherWayTest() {
        assertEquals(
            funListOf(1, 2, 3, 4, 5).toStringInAnotherWay(""),
            "[1, 2, 3, 4, 5]"
        )
    }
}
