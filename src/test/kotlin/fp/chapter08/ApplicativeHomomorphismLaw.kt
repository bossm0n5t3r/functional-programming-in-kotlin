package fp.chapter08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * Homomorphism
 *
 * pure(function) apply pure(x) = pure(function(x))
 */
class ApplicativeHomomorphismLaw {
    @Test
    fun run() {
        val function = { x: Int -> x * 2 }
        val x = 10

        val leftMaybe = Maybe.pure(function) apply Maybe.pure(x)
        val rightMaybe = Maybe.pure(function(x))
        assertEquals(leftMaybe.toString(), rightMaybe.toString())

        val leftTree = Tree.pure(function) apply Tree.pure(x)
        val rightTree = Tree.pure(function(x))
        assertEquals(leftTree.toString(), rightTree.toString())

        val leftEither = Either.pure(function) apply Either.pure(x)
        val rightEither = Either.pure(function(x))
        assertEquals(leftEither.toString(), rightEither.toString())
    }
}
