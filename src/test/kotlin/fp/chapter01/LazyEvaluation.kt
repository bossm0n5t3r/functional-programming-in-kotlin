package fp.chapter01

import org.junit.jupiter.api.Test

class LazyEvaluation {
    private val lazyValue: String by lazy {
        println("시간이 오래 걸리는 작업")
        "hello"
    }

    @Test
    fun run() {
        // 시간이 오래 걸리는 작업
        // hello
        // hello
        println(lazyValue)
        println(lazyValue)
    }
}
