package fp.chapter09.exercises

import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

fun <T> Tree<T>.contains(value: T): Boolean = foldMap({ it == value }, AnyMonoid())

class P0913TreeWithFoldMapContains {
    @Test
    fun run() {
        val tree = Node(
            1,
            Cons(
                Node(2, Cons(Node(3), Cons(Node(4), Nil))),
                Cons(Node(5, Cons(Node(6), Cons(Node(7), Nil))), Nil)
            )
        )

        val tree2 = Node(
            'a',
            Cons(
                Node('b', Cons(Node('c'), Cons(Node('d'), Nil))),
                Cons(Node('e', Cons(Node('f'), Cons(Node('g'), Nil))), Nil)
            )
        )

        assertTrue(tree.contains(1))
        assertFalse(tree.contains(8))

        assertTrue(tree2.contains('c'))
        assertFalse(tree2.contains('x'))
    }
}
