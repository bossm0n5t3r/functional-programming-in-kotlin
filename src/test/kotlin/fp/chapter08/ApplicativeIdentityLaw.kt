package fp.chapter08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun identity() = { x: Int -> x }

class ApplicativeIdentityLaw {
    @Test
    fun run() {
        val maybeAf = Just(10)
        val leftMaybe = Maybe.pure(identity()) apply maybeAf
        assertEquals(leftMaybe.toString(), maybeAf.toString())

        val treeAf = Node(1, listOf(Node(2), Node(3)))
        val leftTree = Tree.pure(identity()) apply treeAf
        assertEquals(leftTree.toString(), treeAf.toString())

        val eitherAf = Right(10)
        val leftEither = Either.pure(identity()) apply eitherAf
        assertEquals(leftEither.toString(), eitherAf.toString())
    }
}
