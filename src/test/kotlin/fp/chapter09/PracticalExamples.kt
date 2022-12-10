package fp.chapter09

import fp.chapter05.FunList
import fp.chapter05.foldLeft
import fp.chapter05.funListOf
import fp.chapter09.exercises.AnyMonoid
import fp.chapter09.exercises.FunListMonoid
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

fun <A> BinaryTree<A>.contains(value: A) = foldMap({ it == value }, AnyMonoid())

fun <A> BinaryTree<A>.toFunList(): FunList<A> = foldMap({ funListOf(it) }, FunListMonoid())

class PracticalExamples {
    private fun <T> printFunList(list: FunList<T>) = println(list.toStringByFoldLeft())

    private fun <T> FunList<T>.toStringByFoldLeft(): String =
        "[${foldLeft("") { acc, x -> "$acc, $x" }.drop(2)}]"

    @Test
    fun run() {
        val tree = Node(
            "a",
            Node(
                "b",
                Node("c"), Node("d")
            ),
            Node(
                "e",
                Node("f"), Node("g")
            )
        )

        assertTrue(tree.contains("c"))
        assertFalse(tree.contains("z"))

        printFunList(tree.toFunList()) // [c, b, d, a, f, e, g]
    }
}
