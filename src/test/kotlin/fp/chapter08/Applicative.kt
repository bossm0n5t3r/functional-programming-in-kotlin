package fp.chapter08

import fp.chapter07.Functor

interface Applicative<out A> : Functor<A> {
    fun <V> pure(value: V): Applicative<V>
    infix fun <B> apply(ff: Applicative<(A) -> B>): Applicative<B>
}
