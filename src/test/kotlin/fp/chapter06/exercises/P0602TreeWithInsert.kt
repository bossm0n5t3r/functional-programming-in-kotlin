package fp.chapter06.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0602TreeWithInsert {
    sealed class Tree<out T>
    object EmptyTree : Tree<Nothing>()

    data class Node<T>(
        val value: T,
        val left: Tree<T> = EmptyTree,
        val right: Tree<T> = EmptyTree,
    ) : Tree<T>()

    private fun Tree<Int>.insert(elem: Int): Tree<Int> = when (this) {
        EmptyTree -> Node(elem, EmptyTree, EmptyTree)
        is Node -> when {
            elem <= value -> Node(value, left.insert(elem), right)
            else -> Node(value, left, right.insert(elem))
        }
    }

    @Test
    fun run() {
        val tree1 = EmptyTree.insert(5)

        assertEquals(
            tree1,
            Node(5, EmptyTree, EmptyTree)
        )

        val tree2 = tree1.insert(3)
        assertEquals(
            tree2,
            Node(
                value = 5,
                left = Node(3),
                right = EmptyTree,
            )
        )

        val tree3 = tree2.insert(10)
        assertEquals(
            tree3,
            Node(
                value = 5,
                left = Node(3),
                right = Node(10),
            )
        )

        val tree4 = tree3.insert(20)
        assertEquals(
            tree4,
            Node(
                value = 5,
                left = Node(3),
                right = Node(
                    10,
                    EmptyTree,
                    Node(20)
                ),
            )
        )

        val tree5 = tree4.insert(4)
        assertEquals(
            tree5,
            Node(
                5,
                Node(
                    3,
                    EmptyTree,
                    Node(4, EmptyTree, EmptyTree)
                ),
                Node(
                    10,
                    EmptyTree,
                    Node(20, EmptyTree, EmptyTree)
                )
            )
        )

        val tree6 = tree5.insert(2)
        assertEquals(
            tree6,
            Node(
                5,
                Node(
                    3,
                    Node(2, EmptyTree, EmptyTree),
                    Node(4, EmptyTree, EmptyTree)
                ),
                Node(
                    10,
                    EmptyTree,
                    Node(20, EmptyTree, EmptyTree)
                )
            )
        )

        val tree7 = tree6.insert(8)
        assertEquals(
            tree7,
            Node(
                5,
                Node(
                    3,
                    Node(2, EmptyTree, EmptyTree),
                    Node(4, EmptyTree, EmptyTree)
                ),
                Node(
                    10,
                    Node(8, EmptyTree, EmptyTree),
                    Node(20, EmptyTree, EmptyTree)
                )
            )
        )
    }
}
