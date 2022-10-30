package fp.chapter07

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <T> identity(x: T): T = x

class FunctorFirstLaw {
    @Test
    fun firstLawOfMaybe() {
        assertEquals(Nothing.fmap { nothing: Nothing -> identity(nothing) }, identity(Nothing))
        assertEquals(Just(5).fmap { identity(it) }, identity(Just(5)))
    }

    @Test
    fun firstLawOfTree() {
        val tree =
            Node(1, Node(2, EmptyTree, EmptyTree), Node(3, EmptyTree, EmptyTree))

        assertEquals(EmptyTree.fmap { emptyTree: EmptyTree -> identity(emptyTree) }, identity(EmptyTree))
        assertEquals(tree.fmap { identity(it) }, identity(tree))
    }

    @Test
    fun firstLawOfEither() {
        assertEquals(Left("error").fmap { left: Nothing -> identity(left) }, identity(Left("error")))
        assertEquals(Right(5).fmap { identity(it) }, identity(Right(5)))
    }
}
