package fp.chapter11.exercises

import fp.chapter10.Just
import fp.chapter10.Maybe
import fp.chapter10.Nothing
import fp.chapter10.exercises.FunStream
import fp.chapter10.exercises.funStreamOf
import fp.chapter11.logging.WriterMonad
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun gcd(
    x: Int,
    y: Int,
    maybeWriteMonad: Maybe<WriterMonad<Pair<Int, Int>>> = Nothing
): FunStream<String> = when (maybeWriteMonad) {
    is Nothing -> {
        when {
            x == 0 -> funStreamOf("Finished with $y")
            x % y == 0 -> gcd(0, y, Maybe.pure(x to y withLog "$x mod $y = ${x % y}"))
            x > y -> gcd(y, x % y, Maybe.pure(x to y withLog "$x mod $y = ${x % y}"))
            else -> gcd(y, x, Maybe.pure(y to x withLog "$x mod $y = ${x % y}"))
        }
    }

    is Just -> {
        when {
            x == 0 -> maybeWriteMonad.value.flatMap { x to y withLog "Finished with $y" }.logs
            x % y == 0 -> gcd(0, y, maybeWriteMonad.fmap { it.flatMap { (x to y withLog "$x mod $y = ${x % y}") } })
            x > y -> gcd(y, x % y, maybeWriteMonad.fmap { it.flatMap { y to x % y withLog "$x mod $y = ${x % y}" } })
            else -> gcd(y, x, maybeWriteMonad.fmap { it.flatMap { y to x withLog "$x mod $y = ${x % y}" } })
        }
    }
}

fun gcd2(x: Int, y: Int, writeMonad: WriterMonad<Pair<Int, Int>>? = null): FunStream<String> = when (writeMonad) {
    null -> {
        when {
            x == 0 -> funStreamOf("Finished with $y")
            x % y == 0 -> gcd2(0, y, x to y withLog "$x mod $y = ${x % y}")
            x > y -> gcd2(y, x % y, x to y withLog "$x mod $y = ${x % y}")
            else -> gcd2(y, x, y to x withLog "$x mod $y = ${x % y}")
        }
    }

    else -> {
        when {
            x == 0 -> writeMonad.flatMap { x to y withLog "Finished with $y" }.logs
            x % y == 0 -> gcd2(0, y, writeMonad.flatMap { x to y withLog "$x mod $y = ${x % y}" })
            x > y -> gcd2(y, x % y, writeMonad.flatMap { y to x % y withLog "$x mod $y = ${x % y}" })
            else -> gcd2(y, x, writeMonad.flatMap { y to x withLog "$x mod $y = ${x % y}" })
        }
    }
}

private infix fun <T> T.withLog(log: String): WriterMonad<T> = WriterMonad(this, funStreamOf(log))

class P1102WriterMonadGCD {
    @Test
    fun run() {
        assertEquals(gcd(60, 48), funStreamOf("60 mod 48 = 12", "48 mod 12 = 0", "Finished with 12"))
        assertEquals(gcd2(60, 48), funStreamOf("60 mod 48 = 12", "48 mod 12 = 0", "Finished with 12"))
        assertEquals(gcd(48, 60), funStreamOf("48 mod 60 = 48", "60 mod 48 = 12", "48 mod 12 = 0", "Finished with 12"))
        assertEquals(gcd2(48, 60), funStreamOf("48 mod 60 = 48", "60 mod 48 = 12", "48 mod 12 = 0", "Finished with 12"))
        assertEquals(gcd(60, 5), funStreamOf("60 mod 5 = 0", "Finished with 5"))
        assertEquals(gcd2(60, 5), funStreamOf("60 mod 5 = 0", "Finished with 5"))
        assertEquals(gcd(61, 3), funStreamOf("61 mod 3 = 1", "3 mod 1 = 0", "Finished with 1"))
        assertEquals(gcd2(61, 3), funStreamOf("61 mod 3 = 1", "3 mod 1 = 0", "Finished with 1"))
        assertEquals(
            gcd(63, 24),
            funStreamOf(
                "63 mod 24 = 15", "24 mod 15 = 9", "15 mod 9 = 6", "9 mod 6 = 3", "6 mod 3 = 0",
                "Finished with 3"
            )
        )
        assertEquals(
            gcd2(63, 24),
            funStreamOf(
                "63 mod 24 = 15", "24 mod 15 = 9", "15 mod 9 = 6", "9 mod 6 = 3", "6 mod 3 = 0",
                "Finished with 3"
            )
        )
    }
}
