package fp.chapter05.exercises

import fp.chapter05.FunList.Cons
import fp.chapter05.FunList.Nil
import org.junit.jupiter.api.Test

class P0501IntList {
    @Test
    fun run() {
        val intList = Cons(1, Cons(2, Cons(3, Cons(4, Cons(5, Nil)))))
        println(intList)
    }
}
