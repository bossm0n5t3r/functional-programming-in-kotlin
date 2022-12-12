package fp.chapter10.exercises

import fp.chapter10.Monad
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

sealed class FunTree<out A> : Monad<A> {

    object EmptyTree : FunTree<Nothing>()

    data class Node<out A>(
        val value: A,
        val leftTree: FunTree<A> = EmptyTree,
        val rightTree: FunTree<A> = EmptyTree,
    ) : FunTree<A>()

    companion object {
        fun <A> pure(value: A): Node<A> = Node(0).pure(value) as Node<A>
    }

    override infix fun <V> pure(value: V): Monad<V> = Node(value = value)

    override infix fun <B> flatMap(f: (A) -> Monad<B>): Monad<B> = when (this) {
        is EmptyTree -> EmptyTree
        is Node -> (f(value) as FunTree) mappend
            (leftTree.flatMap(f) as FunTree) mappend
            (rightTree.flatMap(f) as FunTree)
    }

    override fun <B> leadTo(m: Monad<B>): Monad<B> = flatMap { m }
}

fun <A> FunTree<A>.mempty(): FunTree<A> = FunTree.EmptyTree

infix fun <A> FunTree<A>.mappend(other: FunTree<A>): FunTree<A> = when (this) {
    is FunTree.EmptyTree -> other
    is FunTree.Node -> when (other) {
        is FunTree.EmptyTree -> this
        is FunTree.Node -> when (leftTree) {
            is FunTree.EmptyTree -> FunTree.Node(value, other, rightTree)
            is FunTree.Node -> when (rightTree) {
                is FunTree.EmptyTree -> FunTree.Node(value, leftTree, other)
                is FunTree.Node -> FunTree.Node(value, leftTree.mappend(other), rightTree)
            }
        }
    }
}

class P1015FunTree {
    @Test
    fun run() {
        val node1 = FunTree.pure(1)
        val node2 = FunTree.pure(2)
        val node3 = FunTree.pure(3)
        val node4 = FunTree.pure(4)
        val node5 = FunTree.pure(5)

        val fmapF: (Int) -> String = { "value is $it" }
        val flatMapF: (Int) -> FunTree<String> = {
            FunTree.Node("value is $it", FunTree.Node("value is ${it + 1}"), FunTree.Node("value is ${it + 2}"))
        }

        assertEquals(node1, FunTree.Node(1))
        assertEquals(node2, FunTree.Node(2))
        assertEquals(node3, FunTree.Node(3))
        assertEquals(node4, FunTree.Node(4))
        assertEquals(node5, FunTree.Node(5))

        assertEquals(node1.fmap(fmapF), FunTree.Node("value is 1"))
        assertEquals(
            node1.flatMap(flatMapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node("value is 2"),
                FunTree.Node("value is 3")
            )
        )

        val tree1 = node1 mappend node2
        assertEquals(tree1, FunTree.Node(1, FunTree.Node(2)))
        assertEquals(tree1.fmap(fmapF), FunTree.Node("value is 1", FunTree.Node("value is 2")))
        assertEquals(
            tree1.flatMap(flatMapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node(
                    "value is 2",
                    FunTree.Node(
                        "value is 2",
                        FunTree.Node("value is 3"),
                        FunTree.Node("value is 4")
                    )
                ),
                FunTree.Node("value is 3")
            )
        )
        // FIXME Check this assertEquals
        assertEquals(
            tree1.leadTo(FunTree.pure(7)),
            FunTree.pure(7)
                mappend FunTree.pure(7)
        )

        val tree2 = tree1 mappend node3
        assertEquals(
            tree2,
            FunTree.Node(
                1,
                FunTree.Node(2), FunTree.Node(3)
            )
        )
        assertEquals(
            tree2.fmap(fmapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node("value is 2"), FunTree.Node("value is 3")
            )
        )
        assertEquals(
            tree2.flatMap(flatMapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node(
                    "value is 2",
                    FunTree.Node(
                        "value is 2",
                        FunTree.Node("value is 3"),
                        FunTree.Node("value is 4")
                    ),
                    FunTree.Node(
                        "value is 3",
                        FunTree.Node("value is 4"),
                        FunTree.Node("value is 5")
                    )

                ),
                FunTree.Node("value is 3")
            )
        )
        // FIXME Check this assertEquals
        assertEquals(
            tree2.leadTo(FunTree.pure(7)),
            FunTree.pure(7)
                mappend FunTree.pure(7)
                mappend FunTree.pure(7)
        )

        val tree3 = tree2 mappend node4
        assertEquals(
            tree3,
            FunTree.Node(
                1,
                FunTree.Node(
                    2,
                    FunTree.Node(4)
                ),
                FunTree.Node(3)
            )
        )
        assertEquals(
            tree3.fmap(fmapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node(
                    "value is 2",
                    FunTree.Node("value is 4")
                ),
                FunTree.Node("value is 3")
            )
        )
        assertEquals(
            tree3.flatMap(flatMapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node(
                    "value is 2",
                    FunTree.Node(
                        "value is 2",
                        FunTree.Node(
                            "value is 3",
                            FunTree.Node(
                                "value is 4",
                                FunTree.Node("value is 5"),
                                FunTree.Node("value is 6")
                            )
                        ),
                        FunTree.Node("value is 4")
                    ),
                    FunTree.Node(
                        "value is 3",
                        FunTree.Node("value is 4"),
                        FunTree.Node("value is 5")
                    )
                ),
                FunTree.Node("value is 3")
            )
        )
        // FIXME Check this assertEquals
        assertEquals(
            tree3.leadTo(FunTree.pure(7)),
            FunTree.pure(7)
                mappend FunTree.pure(7)
                mappend FunTree.pure(7)
                mappend FunTree.pure(7)
        )

        val tree4 = tree3 mappend node5
        assertEquals(
            tree4,
            FunTree.Node(
                1,
                FunTree.Node(
                    2,
                    FunTree.Node(4), FunTree.Node(5)
                ),
                FunTree.Node(3)
            )
        )
        assertEquals(
            tree4.fmap(fmapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node(
                    "value is 2",
                    FunTree.Node("value is 4"), FunTree.Node("value is 5")
                ),
                FunTree.Node("value is 3")
            )
        )
        assertEquals(
            tree4.flatMap(flatMapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node(
                    "value is 2",
                    FunTree.Node(
                        "value is 2",
                        FunTree.Node(
                            "value is 3",
                            FunTree.Node(
                                "value is 4",
                                FunTree.Node("value is 5"),
                                FunTree.Node("value is 6")
                            ),
                            FunTree.Node(
                                "value is 5",
                                FunTree.Node("value is 6"),
                                FunTree.Node("value is 7")
                            )
                        ),
                        FunTree.Node("value is 4")
                    ),
                    FunTree.Node(
                        "value is 3",
                        FunTree.Node("value is 4"),
                        FunTree.Node("value is 5")
                    )
                ),
                FunTree.Node("value is 3")
            )
        )
        // FIXME Check this assertEquals
        assertEquals(
            tree4.leadTo(FunTree.pure(7)),
            FunTree.pure(7)
                mappend FunTree.pure(7)
                mappend FunTree.pure(7)
                mappend FunTree.pure(7)
                mappend FunTree.pure(7)
        )

        val tree5 = tree1 mappend tree3
        assertEquals(
            tree5,
            FunTree.Node(
                1,
                FunTree.Node(2),
                FunTree.Node(
                    1,
                    FunTree.Node(
                        2,
                        FunTree.Node(4)
                    ),
                    FunTree.Node(3)
                )
            )
        )
        assertEquals(
            tree5.fmap(fmapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node("value is 2"),
                FunTree.Node(
                    "value is 1",
                    FunTree.Node(
                        "value is 2",
                        FunTree.Node("value is 4")
                    ),
                    FunTree.Node("value is 3")
                )
            )
        )
        assertEquals(
            tree5.flatMap(flatMapF),
            FunTree.Node(
                "value is 1",
                FunTree.Node(
                    "value is 2",
                    FunTree.Node(
                        "value is 2",
                        FunTree.Node("value is 3"),
                        FunTree.Node("value is 4")
                    ),
                    FunTree.Node(
                        "value is 1",
                        FunTree.Node(
                            "value is 2",
                            FunTree.Node(
                                "value is 2",
                                FunTree.Node(
                                    "value is 3",
                                    FunTree.Node(
                                        "value is 4",
                                        FunTree.Node("value is 5"),
                                        FunTree.Node("value is 6")
                                    )
                                ),
                                FunTree.Node("value is 4")
                            ),
                            FunTree.Node(
                                "value is 3",
                                FunTree.Node("value is 4"),
                                FunTree.Node("value is 5")
                            )
                        ),
                        FunTree.Node("value is 3")
                    )
                ),
                FunTree.Node("value is 3")
            )
        )
        // FIXME Check this assertEquals
        assertEquals(
            tree5.leadTo(FunTree.pure(7)),
            FunTree.Node(
                7,
                FunTree.Node(7),
                FunTree.Node(
                    7,
                    FunTree.Node(
                        7,
                        FunTree.Node(7)
                    ),
                    FunTree.Node(7)
                )
            )
        )
    }
}
