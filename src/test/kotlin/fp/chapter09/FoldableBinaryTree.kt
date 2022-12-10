package fp.chapter09

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class BinaryTree<out A> : Foldable<A> {
    override fun <B> foldLeft(acc: B, f: (B, A) -> B): B = when (this) {
        is EmptyTree -> acc
        is Node -> {
            val leftAcc = leftTree.foldLeft(acc, f)
            val rootAcc = f(leftAcc, value)
            rightTree.foldLeft(rootAcc, f)
        }
    }
}

data class Node<A>(
    val value: A,
    val leftTree: BinaryTree<A> = EmptyTree,
    val rightTree: BinaryTree<A> = EmptyTree
) : BinaryTree<A>()

object EmptyTree : BinaryTree<kotlin.Nothing>()

class FoldableBinaryTree {
    @Test
    fun run() {
        val tree = Node(
            1,
            Node(
                2,
                Node(3), Node(4)
            ),
            Node(
                5,
                Node(6), Node(7)
            )
        )

        assertEquals(tree.foldLeft(0) { a, b -> a + b }, 28)
        assertEquals(tree.foldLeft(1) { a, b -> a * b }, 5040)

        assertEquals(tree.foldMap({ a -> a * 2 }, SumMonoid()), 56)
        assertEquals(tree.foldMap({ a -> a + 1 }, ProductMonoid()), 40320)
    }
}
