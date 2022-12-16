package fp.chapter11.exception

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExceptionHandlingByNullOrSpecificValue {
    private fun divideTenBy(value: Int) = try {
        10 / value
    } catch (e: Exception) {
        -1
    }

    private fun subtractTenBy(value: Int) = 10 - value

    private fun divSubTenBy(value: Int): Int {
        val divByTen = divideTenBy(value)

        if (divByTen == -1) {
            return -1 // -1은 실패를 의미
        }

        return subtractTenBy(divByTen) // -1은 정상적인 결과를 의미
    }

    @Test
    fun run() {
        assertEquals(divideTenBy(0), -1)
        assertEquals(subtractTenBy(10), 0)

        assertEquals(
            expected = when (val result = divSubTenBy(5)) {
                -1 -> "divSubTenBy(5) error"
                else -> "divSubTenBy(5) returns $result"
            },
            actual = "divSubTenBy(5) returns 8"
        )

        assertEquals(
            expected = when (val result = divSubTenBy(0)) {
                -1 -> "divSubTenBy(0) error"
                else -> "divSubTenBy(0) returns $result"
            },
            actual = "divSubTenBy(0) error"
        )
    }
}
