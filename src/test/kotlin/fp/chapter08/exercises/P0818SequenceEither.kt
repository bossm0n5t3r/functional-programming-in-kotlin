package fp.chapter08.exercises

import fp.chapter08.Either
import fp.chapter08.Left
import fp.chapter08.Right
import fp.chapter08.apply
import fp.chapter08.pure
import fp.curried
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class P0818SequenceEither {
    private fun <T> cons() = { x: T, xs: FunList<T> -> Cons(x, xs) }

    private fun <L, R> sequenceAByFoldRight(eitherList: FunList<Either<L, R>>): Either<L, FunList<R>> =
        when (eitherList) {
            is Nil -> Right(Nil)
            is Cons ->
                Either.pure(cons<R>().curried()) apply eitherList.head apply sequenceAByFoldRight(eitherList.tail)
        }

    @Test
    fun run() {
        val eitherList: Cons<Right<Int>> = Cons(Right(1), Cons(Right(2), Cons(Right(3), Nil)))
        assertEquals(sequenceAByFoldRight(eitherList), Right(Cons(1, Cons(2, Cons(3, Nil)))))

        val eitherList2: Cons<Either<String, Int>> =
            Cons(Right(1), Cons(Left("test"), Cons(Right(3), Nil)))
        assertEquals(sequenceAByFoldRight(eitherList2), Left("test"))
    }
}
