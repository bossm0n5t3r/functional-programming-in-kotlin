package fp.chapter11.logging

import org.junit.jupiter.api.Test

class ImperativeLogging {
    private fun imperativeFunction(list: List<Int>): MutableList<Boolean> {
        val newList = mutableListOf<Boolean>()

        for (value: Int in list) {
            val addFive = value + 5
            println("$value + 5")

            val square = addFive * addFive
            println("$addFive * $addFive")

            val isGreaterThan50 = square > 50
            println("$square > 50")

            newList.add(isGreaterThan50)
        }

        return newList
    }

    @Test
    fun run() {
        /**
         * 1 + 5
         * 6 * 6
         * 36 > 50
         * 2 + 5
         * 7 * 7
         * 49 > 50
         * 3 + 5
         * 8 * 8
         * 64 > 50
         * [false, false, true]
         */
        println(imperativeFunction(listOf(1, 2, 3)))
    }
}
