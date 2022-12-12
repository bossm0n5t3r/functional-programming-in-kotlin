package fp.chapter10

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class IOMonadExamples {
    private fun getFirstWord(filePath: String): String = getFirstLine(filePath).split(" ").first()

    private fun getFirstLine(filePath: String): String = File(filePath).readLines().first()

    private fun getFirstWord2(lines: List<String>): String = lines.first().split(" ").first()

    private fun getLines(filePath: String): List<String> = File(filePath).readLines()

    @Test
    fun run() {
        val filePath = ClassLoader.getSystemResource("someArticle.txt").path
        assertEquals(getFirstWord(filePath), "Why")

        val lines = getLines(filePath)
        assertEquals(getFirstWord2(lines), "Why")
    }
}
