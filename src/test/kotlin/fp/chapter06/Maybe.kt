package fp.chapter06

class Maybe {
    sealed class Maybe<T>
    object Nothing : Maybe<kotlin.Nothing>()
    data class Just<T>(val value: T) : Maybe<T>()
}
