package fp.chapter08

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

private fun <P1, P2, P3> compose() = { f: (P2) -> P3, g: (P1) -> P2, v: P1 -> f(g(v)) }

private fun <P1, P2, P3, R> ((P1, P2, P3) -> R).curried(): (P1) -> (P2) -> (P3) -> R = { p1: P1 ->
    { p2: P2 -> { p3: P3 -> this(p1, p2, p3) } }
}

class ApplicativeCompositionLaw {
    @Test
    fun run() {
        val maybeAf1 = Just { x: Int -> x * 2 }
        val maybeAf2 = Just { x: Int -> x + 1 }
        val maybeAf3 = Just(30)
        val leftMaybe = Maybe.pure(compose<Int, Int, Int>().curried()) apply maybeAf1 apply maybeAf2 apply maybeAf3
        val rightMaybe = maybeAf1 apply (maybeAf2 apply maybeAf3)
        assertEquals(leftMaybe.toString(), rightMaybe.toString())

        val treeAf1 = Node({ x: Int -> x * 2 })
        val treeAf2 = Node({ x: Int -> x + 1 })
        val treeAf3 = Node(10)
        val leftTree = Tree.pure(compose<Int, Int, Int>().curried()) apply treeAf1 apply treeAf2 apply treeAf3
        val rightTree = treeAf1 apply (treeAf2 apply treeAf3)
        assertEquals(leftTree.toString(), rightTree.toString())

        val eitherAf1 = Right { x: Int -> x * 2 }
        val eitherAf2 = Right { x: Int -> x + 1 }
        val eitherAf3 = Right(10)
        val leftEither = Either.pure(compose<Int, Int, Int>().curried()) apply eitherAf1 apply eitherAf2 apply eitherAf3
        val rightEither = eitherAf1 apply (eitherAf2 apply eitherAf3)
        assertEquals(leftEither.toString(), rightEither.toString())
    }
}
