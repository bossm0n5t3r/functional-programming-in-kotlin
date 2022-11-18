package fp.chapter08.exercises

import fp.chapter08.Node
import fp.chapter08.Tree
import fp.chapter08.apply
import fp.chapter08.pure
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0813LiftTree {
    private fun <A, B, R> liftA2(binaryFunction: (A, B) -> R): (Node<A>, Node<B>) -> Node<R> =
        { f1: Node<A>, f2: Node<B> -> Tree.pure(binaryFunction.curried()) apply f1 apply f2 }

    @Test
    fun run() {
        val lifted = liftA2 { x: Int, y: Int -> x + y }
        assertEquals(lifted(Node(1), Node(2)), Node(3))

        val lifted2 = liftA2 { x: String, y: String -> x + y }
        assertEquals(lifted2(Node("Hello, "), Node("Kotlin")), Node("Hello, Kotlin"))

        val lifted3 = liftA2 { x: Int, y: String -> x.toString() + y }
        assertEquals(lifted3(Node(10), Node("Kotlin")), Node("10Kotlin"))
    }
}
