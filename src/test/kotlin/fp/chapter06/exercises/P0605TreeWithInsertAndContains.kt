package fp.chapter06.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class P0605TreeWithInsertAndContains {
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

    private fun Tree<Int>.contains(element: Int): Boolean = when (this) {
        EmptyTree -> false
        is Node -> when (element) {
            value -> true
            else -> {
                if (element < value) {
                    left.contains(element)
                } else {
                    right.contains(element)
                }
            }
        }
    }

    @Test
    fun run() {
        assertFalse(EmptyTree.contains(5))

        val tree1 = EmptyTree.insert(5)
        assertTrue(tree1.contains(5))
        assertFalse(tree1.contains(10))

        val tree2 = tree1.insert(3)
        assertTrue(tree2.contains(5))
        assertTrue(tree2.contains(3))
        assertFalse(tree2.contains(10))

        val tree3 = tree2.insert(10)
        assertTrue(tree3.contains(5))
        assertTrue(tree3.contains(3))
        assertTrue(tree3.contains(10))
    }
}
