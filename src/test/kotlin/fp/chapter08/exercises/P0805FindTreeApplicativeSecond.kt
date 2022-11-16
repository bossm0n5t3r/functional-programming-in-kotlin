package fp.chapter08.exercises

import fp.chapter08.Node
import fp.chapter08.Tree
import fp.chapter08.apply
import fp.chapter08.pure
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0805FindTreeApplicativeSecond {
    @Test
    fun run() {
        val tree: Tree<Int> = Node(
            5,
            listOf(
                Node(6),
                Node(
                    7,
                    listOf(
                        Node(8),
                        Node(9),
                    )
                ),
                Node(
                    10,
                    listOf(
                        Node(12),
                        Node(
                            14,
                            listOf(
                                Node(16),
                                Node(18),
                            )
                        ),
                        Node(
                            15,
                            listOf(
                                Node(18),
                                Node(
                                    21,
                                    listOf(
                                        Node(24),
                                        Node(27),
                                    )
                                ),
                            )
                        )
                    )
                ),
                Node(
                    20,
                    listOf(
                        Node(24),
                        Node(
                            28,
                            listOf(
                                Node(32),
                                Node(36),
                            )
                        ),
                    )
                ),
            )
        )

        assertEquals(
            tree,
            Tree.pure({ x: Int, y: Int -> x * y }.curried())
                apply Node(1, listOf(Node(2, listOf(Node(3))), Node(4, listOf())))
                apply Node(5, listOf(Node(6), Node(7, listOf(Node(8), Node(9)))))
        )
    }
}
