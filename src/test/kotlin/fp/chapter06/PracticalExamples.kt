package fp.chapter06

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class PracticalExamples {
    interface LanguageInterface

    class Java : LanguageInterface
    class Kotlin : LanguageInterface
    class Scala : LanguageInterface
    class Haskell : LanguageInterface

    private fun caseLanguageInterface(language: LanguageInterface) = when (language) {
        is Java -> {
            // doSomething
        }
        is Kotlin -> {
            // doSomething
        }
        is Scala -> {
            // doSomething
        }
        else -> {
            throw IllegalArgumentException("invalid type : $language")
        }
    }

    @Test
    fun caseLanguageInterfaceTest() {
        assertDoesNotThrow {
            caseLanguageInterface(Java())
        }
    }

    enum class Language {
        JAVA, KOTLIN, SCALA, HASKELL
    }

    private fun caseLanguageEnum(language: Language) = when (language) {
        Language.JAVA -> {
            // doSomething
        }
        Language.KOTLIN -> {
            // doSomething
        }
        Language.SCALA -> {
            // doSomething
        }
        Language.HASKELL -> {
            // doSomething
        }
    }

    @Test
    fun caseLanguageEnumTest() {
        assertDoesNotThrow {
            caseLanguageEnum(Language.KOTLIN)
        }
    }
}
