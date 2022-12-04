package fp.chapter09

import fp.chapter05.FunList
import fp.chapter05.foldRight

interface Monoid<T> {
    fun mempty(): T
    fun mappend(m1: T, m2: T): T
}

fun <T> Monoid<T>.mconcat(list: FunList<T>): T = list.foldRight(mempty(), ::mappend)
