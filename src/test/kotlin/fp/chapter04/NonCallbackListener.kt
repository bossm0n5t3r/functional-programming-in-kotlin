package fp.chapter04

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class NonCallbackListener {
    val callback: (String) -> (String) -> (String) -> (String) -> (String) -> String = { v1 ->
        { v2 ->
            { v3 ->
                { v4 ->
                    { v5 ->
                        v1 + v2 + v3 + v4 + v5
                    }
                }
            }
        }
    }

    @Test
    fun resolveCallbackHellUsingHigherOrderFunctionsAndCurried() {
        assertEquals(callback("1")("2")("3")("4")("5"), "12345")

        val partialApplied = callback("prefix")(":")
        assertEquals(partialApplied("1")("2")("3"), "prefix:123")
        assertEquals(partialApplied("a")("b")("c"), "prefix:abc")
    }
}
