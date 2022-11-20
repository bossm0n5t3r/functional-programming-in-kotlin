package fp.chapter08.exercises

import fp.chapter08.Node
import fp.chapter08.Tree
import fp.chapter08.apply
import fp.chapter08.pure
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0817SequenceTree {
    private fun <T> cons() = { x: T, xs: FunList<T> -> Cons(x, xs) }

    private fun <T> sequenceAByFoldRight(treeList: FunList<Node<T>>): Node<FunList<T>> =
        when (treeList) {
            is Nil -> Node(Nil)
            is Cons ->
                Tree.pure(cons<T>().curried()) apply treeList.head apply sequenceAByFoldRight(treeList.tail)
        }

    @Test
    fun run() {
        val treeList: Cons<Node<Int>> = Cons(Node(1), Cons(Node(2), Cons(Node(3), Nil)))
        assertEquals(sequenceAByFoldRight(treeList), Node(Cons(1, Cons(2, Cons(3, Nil)))))

        val treeList2: Cons<Node<Int>> = Cons(Node(1, listOf(Node(2), Node(3))), Cons(Node(2), Cons(Node(3), Nil)))
        assertEquals(
            sequenceAByFoldRight(treeList2),
            Node(
                Cons(1, Cons(2, Cons(3, Nil))),
                listOf(
                    Node(Cons(2, Cons(2, Cons(3, Nil)))),
                    Node(Cons(3, Cons(2, Cons(3, Nil))))
                )
            )
        )
    }
}
