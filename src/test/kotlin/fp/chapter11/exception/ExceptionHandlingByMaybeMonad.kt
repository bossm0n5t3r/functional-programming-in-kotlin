package fp.chapter11.exception

import fp.chapter10.Just
import fp.chapter10.Maybe
import fp.chapter10.Nothing
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExceptionHandlingByMaybeMonad {
    private fun divideTenBy(value: Int): Maybe<Int> = try {
        Just(10 / value)
    } catch (e: Exception) {
        Nothing
    }

    private fun subtractTenBy(value: Int) = 10 - value

    private fun divSubTenBy(value: Int) = divideTenBy(value).fmap { subtractTenBy(it) }

    @Test
    fun run() {
        assertEquals(
            expected = when (val result = divSubTenBy(5)) {
                is Nothing -> "divSubTenBy(5) error"
                is Just -> "divSubTenBy(5) returns ${result.value}"
            },
            actual = "divSubTenBy(5) returns 8"
        )

        assertEquals(
            expected = when (val result = divSubTenBy(0)) {
                is Nothing -> "divSubTenBy(0) error"
                is Just -> "divSubTenBy(0) returns ${result.value}"
            },
            actual = "divSubTenBy(0) error"
        )
    }
}
