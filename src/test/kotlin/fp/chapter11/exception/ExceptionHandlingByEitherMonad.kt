package fp.chapter11.exception

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ExceptionHandlingByEitherMonad {
    private fun divideTenBy(value: Int): Either<String, Int> = try {
        Right(10 / value)
    } catch (e: ArithmeticException) {
        Left("divide by zero exception")
    }

    private fun subtractTenBy(value: Int) = 10 - value

    private fun divSubTenBy(value: Int) = divideTenBy(value).fmap { subtractTenBy(it) }

    @Test
    fun run() {
        assertEquals(
            expected = when (val result = divSubTenBy(5)) {
                is Left -> "divSubTenBy(5) error by ${result.value}"
                is Right -> "divSubTenBy(5) returns ${result.value}"
            },
            actual = "divSubTenBy(5) returns 8"
        )

        assertEquals(
            expected = when (val result = divSubTenBy(0)) {
                is Left -> "divSubTenBy(0) error by ${result.value}"
                is Right -> "divSubTenBy(0) returns ${result.value}"
            },
            actual = "divSubTenBy(0) error by divide by zero exception"
        )
    }
}
