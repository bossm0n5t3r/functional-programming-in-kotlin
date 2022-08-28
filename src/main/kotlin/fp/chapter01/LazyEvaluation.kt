package fp.chapter01

fun main() {
    // 시간이 오래 걸리는 작업
    // hello
    // hello
    println(lazyValue)
    println(lazyValue)
}

val lazyValue: String by lazy {
    println("시간이 오래 걸리는 작업")
    "hello"
}
