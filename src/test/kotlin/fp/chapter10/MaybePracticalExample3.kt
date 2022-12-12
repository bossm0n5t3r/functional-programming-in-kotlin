package fp.chapter10

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MaybePracticalExample3 {
    class A3(val b: B3?)
    class B3(val c: C3?)
    class C3(val d: D3?)
    class D3(val value: String?)

    @Test
    fun run() {
        val a = A3(B3(C3(D3("someValue"))))

        assertEquals(a.b?.c?.d?.value ?: "", "someValue")
    }
}
