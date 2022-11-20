package fp.chapter08.exercises

import fp.chapter08.Node
import fp.chapter08.Tree
import fp.chapter08.apply
import fp.chapter08.pure
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0804FindTreeApplicativeFirst {
    @Test
    fun run() {
        val tree: Tree<Int> = Node(
            4,
            listOf(
                Node(8),
                Node(12),
                Node(
                    5,
                    listOf(
                        Node(10),
                        Node(15),
                    )
                ),
                Node(
                    6,
                    listOf(
                        Node(12),
                        Node(18),
                    )
                )
            )
        )

        assertEquals(
            tree,
            Tree.pure({ x: Int, y: Int -> x * y }.curried())
                apply Node(4, listOf(Node(5), Node(6)))
                apply Node(1, listOf(Node(2), Node(3)))
        )
    }
}
