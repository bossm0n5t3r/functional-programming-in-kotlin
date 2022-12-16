package fp.chapter11.exception

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExceptionHandlingByTryMonad {
    private fun divideTenBy(value: Int): Try<Int> = Try.pure(10).fmap { it / value }

    private fun subtractTenBy(value: Int) = 10 / value

    private fun divSubTenBy(value: Int) = divideTenBy(value).fmap { subtractTenBy(it) }

    @Test
    fun run() {
        assertEquals(
            expected = when (val result = divSubTenBy(5)) {
                is Failure -> "divSubTenBy(5) error by ${result.e}"
                is Success -> "divSubTenBy(5) returns ${result.value}"
            },
            actual = "divSubTenBy(5) returns 5"
        )

        assertEquals(
            expected = when (val result = divSubTenBy(0)) {
                is Failure -> "divSubTenBy(0) error by ${result.e}"
                is Success -> "divSubTenBy(0) returns ${result.value}"
            },
            actual = "divSubTenBy(0) error by java.lang.ArithmeticException: / by zero"
        )
    }
}
