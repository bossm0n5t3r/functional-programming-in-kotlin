package fp.chapter07

import fp.compose
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FunctorSecondLaw {
    private val f = { a: Int -> a + 1 }
    private val g = { b: Int -> b * 2 }

    @Test
    fun secondLawOfMaybe() {
        val nothingLeft = Nothing.fmap(f compose g)

        // compile error
        // val nothingRight = Nothing.fmap(f) compose Nothing.fmap(g)

        val nothingRight = Nothing.fmap(g).fmap(f)
        assertEquals(nothingLeft, nothingRight)

        val justLeft = Just(5).fmap(f compose g)
        // compile error
        // val justRight = Just(5).fmap(f) compose Just(0).fmap(g)

        val justRight = Just(5).fmap(g).fmap(f)
        assertEquals(justLeft, justRight)
    }

    @Test
    fun secondLawOfTree() {
        val tree = Node(1, Node(2, EmptyTree, EmptyTree), Node(3, EmptyTree, EmptyTree))

        assertEquals(EmptyTree.fmap(f compose g), EmptyTree.fmap(g).fmap(f))
        assertEquals(tree.fmap(f compose g), tree.fmap(g).fmap(f))
    }

    @Test
    fun secondLawOfEither() {
        assertEquals(Left("error").fmap(f compose g), Left("error").fmap(g).fmap(f))
        assertEquals(Right(5).fmap(f compose g), Right(5).fmap(g).fmap(f))
    }
}
