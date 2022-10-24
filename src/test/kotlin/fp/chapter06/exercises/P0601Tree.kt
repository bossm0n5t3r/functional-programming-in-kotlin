package fp.chapter06.exercises

import org.junit.jupiter.api.Test
import java.util.LinkedList
import java.util.Queue

class P0601Tree {
    sealed class Tree<out T>
    object EmptyTree : Tree<Nothing>()

    data class Node<T>(
        val value: T,
        val left: Tree<T> = EmptyTree,
        val right: Tree<T> = EmptyTree,
    ) : Tree<T>()

    private fun <T> Node<out T>.print() {
        val queue = LinkedList<Tree<T>>() as Queue<Tree<T>>
        queue.add(this)
        var level = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            val values = mutableListOf<String>()
            var containsOnlyEmptyTree = true
            println("==== level: ${level++}")
            repeat(size) {
                val cur = queue.poll()
                if (cur as Tree<T> != EmptyTree) {
                    val (value, left, right) = cur as Node<T>
                    values.add(value.toString())
                    queue.add(left)
                    queue.add(right)
                    containsOnlyEmptyTree = false
                } else {
                    values.add("EMPTY")
                    queue.add(EmptyTree)
                    queue.add(EmptyTree)
                }
            }
            println(values.joinToString(", "))
            if (containsOnlyEmptyTree) return
        }
    }

    @Test
    fun run() {
        val tree = Node(1, Node(2, EmptyTree, Node(4)), Node(3))
        tree.print()
    }
}
