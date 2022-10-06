package fp.chapter04.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0401InvokeOrElseOrElse {
    class PartialFunction<in P, out R>(
        private val condition: (P) -> Boolean,
        private val f: (P) -> R,
    ) : (P) -> R {

        override fun invoke(p: P): R = when {
            condition(p) -> f(p)
            else -> throw IllegalArgumentException("$p isn't supported.")
        }

        fun isDefinedAt(p: P): Boolean = condition(p)

        fun invokeOrElse(p: P, default: @UnsafeVariance R): R = if (isDefinedAt(p)) invoke(p) else default

        fun orElse(that: PartialFunction<@UnsafeVariance P, @UnsafeVariance R>): PartialFunction<P, R> =
            PartialFunction(
                condition = { this.isDefinedAt(it) || that.isDefinedAt(it) },
                f = {
                    when {
                        this.isDefinedAt(it) -> this(it)
                        that.isDefinedAt(it) -> that(it)
                        else -> throw IllegalArgumentException("$it isn't defined")
                    }
                }
            )
    }

    private fun <P, R> ((P) -> R).toPartialFunction(definedAt: (P) -> Boolean): PartialFunction<P, R> =
        PartialFunction(definedAt, this)

    @Test
    fun run() {
        val condition: (Int) -> Boolean = { 0 == it.rem(2) }
        val body: (Int) -> String = { "$it is even" }

        val isEven = body.toPartialFunction(condition)
        val isOdd = { i: Int -> "$i is odd" }.toPartialFunction { !condition(it) }

        listOf(1, 2, 3).forEach {
            assertEquals(
                isEven.invokeOrElse(it, "$it is odd"),
                if (isEven.isDefinedAt(it)) {
                    "$it is even"
                } else {
                    "$it is odd"
                }
            )
        }

        listOf(1, 2, 3).forEach {
            assertEquals(
                isEven.orElse(isOdd)(it),
                if (isEven.isDefinedAt(it)) {
                    "$it is even"
                } else {
                    "$it is odd"
                }
            )
        }
    }
}
