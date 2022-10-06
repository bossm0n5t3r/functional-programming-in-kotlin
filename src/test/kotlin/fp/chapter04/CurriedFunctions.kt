package fp.chapter04

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CurriedFunctions {
    private fun multiThree(a: Int, b: Int, c: Int): Int = a * b * c

    private fun multiThree(a: Int) = { b: Int -> { c: Int -> a * b * c } }

    @Test
    fun multiThreeTest() {
        /**
         * 함수형 프로그래밍에서 복잡해 보이는 커링을 사용하는 이유는 무엇일까?
         *
         * 커링의 장점 중 하나는 이런 부분 적용 함수를 다양하게 재사용할 수 있다는 점
         * 또한 마지막 매개변수가 입력될 때까지 함수를 실행을 늦출 수 있다.
         */
        assertEquals(multiThree(1, 2, 3), 6)

        val partial1 = multiThree(1)
        val partial2 = partial1(2)
        val partial3 = partial2(3)

        assertEquals(partial3, 6)

        assertEquals(multiThree(1)(2)(3), 6)
    }

    private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R =
        { p1: P1 -> { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } } }

    private fun <P1, P2, P3, R> ((P1) -> (P2) -> (P3) -> R).uncurried(): (P1, P2, P3) -> R =
        { p1: P1, p2: P2, p3: P3 -> this(p1)(p2)(p3) }

    @Test
    fun curriedFunctionsInKotlin() {
        val multiThree = { a: Int, b: Int, c: Int -> a * b * c }
        val curried = multiThree.curried()
        assertEquals(curried(1)(2)(3), 6)

        val uncurried = curried.uncurried()
        assertEquals(uncurried(1, 2, 3), 6)
    }
}
