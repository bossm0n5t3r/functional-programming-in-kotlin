package fp.chapter10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MaybePracticalExample2 {
    class A2(val b: Maybe<B2>)
    class B2(val c: Maybe<C2>)
    class C2(val d: Maybe<D2>)
    class D2(val value: Maybe<String>)

    private fun getValueOfD2(a: A2): Maybe<String> = a.b
        .flatMap { it.c }
        .flatMap { it.d }
        .flatMap { it.value }

    @Test
    fun run() {
        val a = A2(Just(B2(Just(C2(Just(D2(Just("someValue"))))))))
        val result = when (val maybe = getValueOfD2(a)) {
            is Just -> maybe.value
            Nothing -> ""
        }

        assertEquals(result, "someValue")
    }
}
