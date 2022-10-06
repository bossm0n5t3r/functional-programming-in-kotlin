package fp.chapter03

import fp.head
import fp.tail
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PracticalExamples {
    private fun <T> powerset(s: Set<T>): Set<Set<T>> = when {
        s.isEmpty() -> setOf(setOf())
        else -> {
            val head = s.head()
            val restSet = powerset(s.tail())
            restSet + restSet.map { setOf(head) + it }.toSet()
        }
    }

    @Test
    fun powersetSimpleTest() {
        assertThat(powerset(setOf(1, 2, 3)))
            .containsExactlyInAnyOrderElementsOf(
                setOf(
                    emptySet(),
                    setOf(1),
                    setOf(2),
                    setOf(3),
                    setOf(1, 2),
                    setOf(2, 3),
                    setOf(3, 1),
                    setOf(1, 2, 3),
                )
            )
    }

    private tailrec fun <T> powerset(s: Set<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
        s.isEmpty() -> acc
        else -> powerset(s.tail(), acc + acc.map { it + s.head() })
    }

    @Test
    fun powersetWithTailRecTest() {
        assertThat(powerset(setOf(1, 2, 3), setOf(setOf())))
            .containsExactlyInAnyOrderElementsOf(
                setOf(
                    emptySet(),
                    setOf(1),
                    setOf(2),
                    setOf(3),
                    setOf(1, 2),
                    setOf(2, 3),
                    setOf(3, 1),
                    setOf(1, 2, 3),
                )
            )
    }

    private fun <T> Collection<T>.powerset(): Set<Set<T>> = powerset(this, setOf(setOf()))
    private tailrec fun <T> powerset(s: Collection<T>, acc: Set<Set<T>>): Set<Set<T>> = when {
        s.isEmpty() -> acc
        else -> powerset(s.tail(), acc + acc.map { it + s.head() })
    }

    @Test
    fun powersetExtensionFunctionTest() {
        assertThat(setOf(1, 2, 3).powerset())
            .containsExactlyInAnyOrderElementsOf(
                setOf(
                    emptySet(),
                    setOf(1),
                    setOf(2),
                    setOf(3),
                    setOf(1, 2),
                    setOf(2, 3),
                    setOf(3, 1),
                    setOf(1, 2, 3),
                )
            )

        assertThat(listOf(1, 2, 3).powerset())
            .containsExactlyInAnyOrderElementsOf(
                setOf(
                    emptySet(),
                    setOf(1),
                    setOf(2),
                    setOf(3),
                    setOf(1, 2),
                    setOf(2, 3),
                    setOf(3, 1),
                    setOf(1, 2, 3),
                )
            )
    }
}
