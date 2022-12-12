package fp.chapter10.exercises

import fp.chapter10.Just
import fp.chapter10.Maybe
import fp.chapter10.Nothing
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class A4(val b: Maybe<B4>)
class B4(val c: Maybe<C4>)
class C4(val d: D4)
class D4(val value: Maybe<String>)

fun getValueOfD4(a: A4): Maybe<String> = a.b
    .flatMap { it.c }
    .fmap { it.d }
    .flatMap { it.value }

fun getValueOfD4_2(a: A4): Maybe<String> = a.b
    .flatMap { it.c }
    .flatMap { it.d.value }

class P1003MaybeChain {
    @Test
    fun run() {
        val a1 = A4(Just(B4(Just(C4(D4(Just("FP")))))))
        assertEquals(Just("FP"), getValueOfD4(a1))
        assertEquals(Just("FP"), getValueOfD4_2(a1))

        val a2 = A4(Nothing)
        assertEquals(Nothing, getValueOfD4(a2))
        assertEquals(Nothing, getValueOfD4_2(a2))

        val a3 = A4(Just(B4(Nothing)))
        assertEquals(Nothing, getValueOfD4(a3))
        assertEquals(Nothing, getValueOfD4_2(a3))

        val a4 = A4(Just(B4(Just(C4(D4(Just("FP")))))))
        assertEquals(Just("FP"), getValueOfD4(a4))
        assertEquals(Just("FP"), getValueOfD4_2(a4))

        val a5 = A4(Just(B4(Just(C4(D4(Nothing))))))
        assertEquals(Nothing, getValueOfD4(a5))
        assertEquals(Nothing, getValueOfD4_2(a5))
    }
}
