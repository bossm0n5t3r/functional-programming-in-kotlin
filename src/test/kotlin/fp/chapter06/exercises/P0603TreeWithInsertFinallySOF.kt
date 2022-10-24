package fp.chapter06.exercises

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class P0603TreeWithInsertFinallySOF {
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
        assertThrows<StackOverflowError> {
            (1..100_000).fold(EmptyTree as Tree<Int>) { acc, i ->
                acc.insert(i)
            }
        }
    }
}
