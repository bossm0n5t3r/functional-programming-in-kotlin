package fp.chapter08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * pure(function) apply af = af.fmap(function)
 */
class ApplicativeAdditionalLaw {
    @Test
    fun run() {
        val function = { x: Int -> x * 2 }

        val maybeAf = Just(10)
        val leftMaybe = Maybe.pure(function) apply maybeAf
        val rightMaybe = maybeAf.fmap(function)
        assertEquals(leftMaybe.toString(), rightMaybe.toString())

        val treeAf = Node(1, listOf(Node(2), Node(3)))
        val leftTree = Tree.pure(function) apply treeAf
        val rightTree = treeAf.fmap(function)
        assertEquals(leftTree.toString(), rightTree.toString())

        val eitherAf = Right(10)
        val leftEither = Either.pure(function) apply eitherAf
        val rightEither = eitherAf.fmap(function)
        assertEquals(leftEither.toString(), rightEither.toString())
    }
}
