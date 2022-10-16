package fp.chapter05.exercises

import fp.chapter05.FunList
import fp.chapter05.addHead
import fp.chapter05.funListOf
import fp.chapter05.getHead
import fp.chapter05.getTail
import fp.chapter05.reverse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class P0513Zip {
    private tailrec fun <T, R> FunList<T>.zip(
        other: FunList<R>,
        acc: FunList<Pair<T, R>> = FunList.Nil,
    ): FunList<Pair<T, R>> = when {
        this === FunList.Nil || other === FunList.Nil -> acc.reverse()
        else -> getTail().zip(other.getTail(), acc.addHead(getHead() to other.getHead()))
    }

    @Test
    fun run() {
        val odd = funListOf(1, 3, 5)
        val even = funListOf(2, 4, 6)
        assertThat(odd.zip(even)).isEqualTo(funListOf(1 to 2, 3 to 4, 5 to 6))
    }
}
