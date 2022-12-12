package fp.chapter10.exercises

sealed class FunStream<out A> {
    object Nil : FunStream<Nothing>()
    data class Cons<out A>(val head: () -> A, val tail: () -> FunStream<A>) : FunStream<A>() {
        override fun equals(other: Any?): Boolean =
            if (other is Cons<*>) {
                if (head() == other.head()) {
                    tail() == other.tail()
                } else {
                    false
                }
            } else {
                false
            }

        override fun hashCode(): Int {
            var result = head.hashCode()
            result = 31 * result + tail.hashCode()
            return result
        }
    }
}

class P1008FunStream
