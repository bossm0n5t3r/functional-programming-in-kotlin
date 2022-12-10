package fp.chapter09.exercises

import fp.chapter05.FunList
import fp.chapter05.funListOf
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

fun <A> Tree<A>.toFunList(): FunList<A> = foldMap({ funListOf(it) }, FunListMonoid())

class P0914FunListToBinaryTree {
    @Test
    fun run() {
        val tree =
            Node(
                1,
                Cons(
                    Node(
                        2,
                        Cons(
                            Node(3),
                            Cons(
                                Node(4),
                                Nil
                            )
                        )
                    ),
                    Cons(
                        Node(
                            5,
                            Cons(
                                Node(6),
                                Cons(
                                    Node(7),
                                    Nil
                                )
                            )
                        ),
                        Nil
                    )
                )
            )

        val tree2 =
            Node(
                'a',
                Cons(
                    Node(
                        'b',
                        Cons(
                            Node('c'),
                            Cons(
                                Node('d'),
                                Nil
                            )
                        )
                    ),
                    Cons(
                        Node(
                            'e',
                            Cons(
                                Node('f'),
                                Cons(
                                    Node('g'),
                                    Nil
                                )
                            )
                        ),
                        Nil
                    )
                )
            )

        val tree3 =
            Node(
                1,
                Cons(
                    Node(
                        2,
                        Cons(
                            Node(4),
                            Cons(
                                Node(
                                    5,
                                    Cons(
                                        Node(7),
                                        Cons(
                                            Node(8),
                                            Nil
                                        )
                                    )
                                ),
                                Nil
                            )
                        )
                    ),
                    Cons(
                        Node(
                            3,
                            Cons(
                                Node(6),
                                Nil
                            )
                        ),
                        Nil
                    )
                )
            )

        assertEquals(tree.toFunList(), funListOf(3, 4, 2, 6, 7, 5, 1))
        assertEquals(tree2.toFunList(), funListOf('c', 'd', 'b', 'f', 'g', 'e', 'a'))
        assertEquals(tree3.toFunList(), funListOf(4, 7, 8, 5, 2, 6, 3, 1))
    }
}
