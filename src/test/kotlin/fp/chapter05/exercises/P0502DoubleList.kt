package fp.chapter05.exercises

import fp.chapter05.FunList.Cons
import fp.chapter05.FunList.Nil
import org.junit.jupiter.api.Test

class P0502DoubleList {
    @Test
    fun run() {
        val doubleList =
            Cons(1.0, Cons(2.0, Cons(3.0, Cons(4.0, Cons(5.0, Nil)))))
        println(doubleList)
    }
}
