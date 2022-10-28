package fp.chapter06.exercises

import fp.chapter05.FunStream
import fp.chapter05.addHead
import fp.chapter05.funStreamOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * TODO : SEE THIS LATER
 *
 * - HARD TO UNDERSTAND (29.10.22)
 */
class P0604TreeWithInsertTailRec {
    sealed class Tree<out T>
    object EmptyTree : Tree<Nothing>()

    data class Node<T>(
        val value: T,
        val left: Tree<T> = EmptyTree,
        val right: Tree<T> = EmptyTree,
    ) : Tree<T>()

    private fun Tree<Int>.insertTailRec(elem: Int): Tree<Int> = rebuild(path(this, elem), elem)

    private fun path(tree: Tree<Int>, value: Int): FunStream<Pair<Tree<Int>, Boolean>> {
        tailrec fun loop(
            tree: Tree<Int>,
            path: FunStream<Pair<Tree<Int>, Boolean>>
        ): FunStream<Pair<Tree<Int>, Boolean>> =
            when (tree) {
                EmptyTree -> path
                is Node -> when {
                    value < tree.value -> loop(tree.left, path.addHead(tree to false))
                    else -> loop(tree.right, path.addHead(tree to true))
                }
            }
        return loop(tree, funStreamOf())
    }

    private fun rebuild(path: FunStream<Pair<Tree<Int>, Boolean>>, value: Int): Tree<Int> {
        tailrec fun loop(path: FunStream<Pair<Tree<Int>, Boolean>>, subTree: Tree<Int>): Tree<Int> =
            when (path) {
                FunStream.Nil -> subTree
                is FunStream.Cons -> when ((path.head()).second) {
                    false -> loop(
                        path.tail(),
                        Node(
                            (path.head().first as Node).value, subTree,
                            (path.head().first as Node).right
                        )
                    )

                    true -> loop(
                        path.tail(),
                        Node(
                            (path.head().first as Node).value,
                            (path.head().first as Node).left, subTree
                        )
                    )
                }
            }

        return loop(path, Node(value, EmptyTree, EmptyTree))
    }

    @Test
    fun run() {
        val tree1 = EmptyTree.insertTailRec(5)

        assertEquals(
            tree1,
            Node(5, EmptyTree, EmptyTree)
        )

        val tree2 = tree1.insertTailRec(3)
        assertEquals(
            tree2,
            Node(
                value = 5,
                left = Node(3),
                right = EmptyTree,
            )
        )

        val tree3 = tree2.insertTailRec(10)
        assertEquals(
            tree3,
            Node(
                value = 5,
                left = Node(3),
                right = Node(10),
            )
        )

        val tree4 = tree3.insertTailRec(20)
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

        val tree5 = tree4.insertTailRec(4)
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

        val tree6 = tree5.insertTailRec(2)
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

        val tree7 = tree6.insertTailRec(8)
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
