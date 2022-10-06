package fp.chapter02

import org.junit.jupiter.api.Test

class Variant {
    interface Box<T>

    /**
     * Kotlin < JVM < Language
     */
    open class Language
    open class JVM : Language()
    class Kotlin : JVM()

    private fun invariant(value: Box<JVM>) {}
    private fun covariant(value: Box<out JVM>) {}
    private fun <T : JVM> upperBound(value: Box<T>) {}
    private fun contravariant(value: Box<in JVM>) {}

    @Test
    fun run() {
        val languageBox = object : Box<Language> {}
        val jvmBox = object : Box<JVM> {}
        val kotlinBox = object : Box<Kotlin> {}

        /**
         * Invariant (무공변)
         */
//        invariant(languageBox)    // compile error
        invariant(jvmBox)
//        invariant(kotlinBox)      // compile error

        /**
         * Covariant (공변)
         */
//        covariant(languageBox)    // compile error
        covariant(jvmBox)
        covariant(kotlinBox)

//        upperBound(languageBox)   // compile error
        upperBound(jvmBox)
        upperBound(kotlinBox)

        /**
         * Contravariant (반공변)
         */
        contravariant(languageBox)
        contravariant(jvmBox)
//        contravariant(kotlinBox)  // compile error
    }

    interface Box2<T> {
        fun read(): T
        fun write(value: T)
    }

//     interface Box2<out T> {
//        fun read(): T
//        fun write(value: T)    // compile error
//     }

//     interface Box2<in T> {
//        fun read(): T         // compile error
//        fun write(value: T)
//     }
}
