package fp.chapter11.testing

import fp.chapter10.FunList
import fp.chapter10.Nil
import fp.chapter10.funListOf
import fp.chapter10.printFunList
import fp.chapter10.reverse
import fp.head
import fp.tail
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.RandomSource
import io.kotest.property.Sample
import io.kotest.property.asSample
import io.kotest.property.forAll
import kotlin.random.Random

private fun reverse(str: String): String = when {
    str.isEmpty() -> ""
    else -> reverse(str.tail()) + str.head()
}

private fun invalidReverse(str: String): String = when {
    str.isEmpty() -> "a"
    else -> invalidReverse(str.tail()) + str.head()
}

class FunListTest : StringSpec({
    "testReverse" {
        forAll { a: String ->
            reverse(reverse(a)) == a
        }
    }

    /**
     * SHOULD FAIL
     */
    "testInvalidReverse" {
        forAll { a: String ->
            invalidReverse(reverse(a)) == a
        }
    }

    "reverseFunList" {
        forAll(500, FunIntListGen()) { list: FunList<Int> ->
            printFunList(list)
            list.reverse().reverse() == list
        }
    }
})

class FunIntListGen : Arb<FunList<Int>>() {
    override fun edgecase(rs: RandomSource): FunList<Int> {
        return Nil
    }

    override fun sample(rs: RandomSource): Sample<FunList<Int>> {
        val listSize = Random.nextInt(0, 10)
        val values = (0 until listSize).map { rs.random.nextInt() }.toTypedArray()
        return funListOf(*values).asSample()
    }
}
