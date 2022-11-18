package fp.chapter08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T, R> of(value: T) = { f: (T) -> R -> f(value) }

/**
 * Interchange
 *
 * af apply pure(x) = pure(of(x)) apply af
 */
class ApplicativeInterchangeLaw {
    @Test
    fun run() {
        val x = 10

        val maybeAf = Just { a: Int -> a * 2 }
        val leftMaybe = maybeAf apply Maybe.pure(x)
        val rightMaybe = Maybe.pure(of<Int, Int>(x)) apply maybeAf
        assertEquals(leftMaybe.toString(), rightMaybe.toString())

        val treeAf = Node({ a: Int -> a * 2 })
        val leftTree = treeAf apply Tree.pure(x)
        val rightTree = Tree.pure(of<Int, Int>(x)) apply treeAf
        assertEquals(leftTree.toString(), rightTree.toString())

        val eitherAf = Right { a: Int -> a * 2 }
        val leftEither = eitherAf apply Either.pure(x)
        val rightEither = Either.pure(of<Int, Int>(x)) apply eitherAf
        assertEquals(leftEither.toString(), rightEither.toString())
    }
}
