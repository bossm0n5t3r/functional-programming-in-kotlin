package fp.chapter06.sumType

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.test.assertEquals

class SumAlgebraicDataType {
    sealed class Shape
    data class Circle(val name: String, val x: Float, val y: Float, val radius: Float) : Shape()
    data class Square(val name: String, val x: Float, val y: Float, val length: Float) : Shape()
    data class Line(val name: String, val x1: Float, val y1: Float, val x2: Float, val y2: Float) : Shape()

    /**
     * 합 타입은 부분의 합이 전체가 되기 때문에 else 구문을 작성할 필요 X
     */
    private fun getGirthLength(shape: Shape): Double = when (shape) {
        is Circle -> 2 * Math.PI * shape.radius
        is Square -> 4 * shape.length.toDouble()
        is Line -> {
            val x2 = (shape.x2 - shape.x1.toDouble()).pow(2.0)
            val y2 = (shape.y2 - shape.y1.toDouble()).pow(2.0)
            sqrt(x2 + y2)
        }
    }

    @Test
    fun getGirthLengthTest() {
        assertEquals(
            getGirthLength(Circle("원", 1.0f, 1.0f, 1.0f)),
            6.283185307179586
        )

        assertEquals(
            getGirthLength(Square("정사각형", 1.0f, 1.0f, 1.0f)),
            4.0
        )

        assertEquals(
            getGirthLength(Line("직선", 1.0f, 1.0f, 4.0f, 5.0f)),
            5.0
        )
    }
}
