package fp.chapter09.exercises

import fp.chapter09.Foldable
import fp.chapter09.SumMonoid
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * TODO : SEE THIS LATER
 *
 * - HARD TO UNDERSTAND (05.12.22)
 */
sealed class Tree<out T> : Foldable<T>

data class Node<out T>(val value: T, val forest: FunList<Node<T>> = Nil) : Tree<T>() {
    override fun <B> foldLeft(acc: B, f: (B, T) -> B): B = when (forest) {
        Nil -> f(acc, value)
        is Cons -> f(loop(forest, f, acc), value)
    }

    private tailrec fun <B> loop(list: FunList<Node<T>>, f: (B, T) -> B, acc: B): B = when (list) {
        Nil -> acc
        is Cons -> loop(list.tail, f, list.head.foldLeft(acc, f))
    }
}

class P0911TreeMonoidWithFoldable {
    @Test
    fun run() {
        val node = Node(1)
        val node2 = Node(1, Cons(Node(2), Nil))
        val node3 = Node(1, Cons(Node(2), Cons(Node(3), Nil)))
        val node4 = Node(
            1,
            Cons(Node(2), Cons(Node(3), Cons(Node(4), Nil)))
        )
        val node5 = Node(
            1,
            Cons(Node(2), Cons(Node(3), Cons(Node(4), Cons(Node(5), Nil))))
        )
        val node6 =
            Node(
                1,
                Cons(
                    Node(2, Cons(Node(6), Nil)),
                    Cons(
                        Node(3),
                        Cons(
                            Node(4),
                            Cons(Node(5), Nil)
                        )
                    )
                )
            )

        val node7 =
            Node(
                1,
                Cons(
                    Node(2, Cons(Node(6), Nil)),
                    Cons(
                        Node(3, Cons(Node(7), Nil)),
                        Cons(
                            Node(4, Cons(Node(8), Nil)),
                            Cons(Node(5, Cons(Node(9), Cons(Node(10), Nil))), Nil)
                        )
                    )
                )
            )

        assertEquals(node.foldLeft(0) { acc, value -> acc + value }, 1)
        assertEquals(node2.foldLeft(0) { acc, value -> acc + value }, 3)
        assertEquals(node3.foldLeft(0) { acc, value -> acc + value }, 6)
        assertEquals(node4.foldLeft(0) { acc, value -> acc + value }, 10)
        assertEquals(node5.foldLeft(0) { acc, value -> acc + value }, 15)
        assertEquals(node6.foldLeft(0) { acc, value -> acc + value }, 21)
        assertEquals(node7.foldLeft(0) { acc, value -> acc + value }, 55)

        assertEquals(node.foldMap({ it * 2 }, SumMonoid()), 2)
        assertEquals(node2.foldMap({ it * 2 }, SumMonoid()), 6)
        assertEquals(node3.foldMap({ it * 2 }, SumMonoid()), 12)
        assertEquals(node4.foldMap({ it * 2 }, SumMonoid()), 20)
        assertEquals(node5.foldMap({ it * 2 }, SumMonoid()), 30)
        assertEquals(node6.foldMap({ it * 2 }, SumMonoid()), 42)
        assertEquals(node7.foldMap({ it * 2 }, SumMonoid()), 110)
    }
}
