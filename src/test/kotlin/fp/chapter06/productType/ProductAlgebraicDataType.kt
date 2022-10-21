package fp.chapter06.productType

import org.junit.jupiter.api.Test
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.test.assertEquals

class ProductAlgebraicDataType {
    class CircleWithProductType(val name: String, val x: Float, val y: Float, val radius: Float)

    open class ShapeWithXY(name: String, x: Float, y: Float)
    class CircleWithShape(name: String, x: Float, y: Float, val radius: Float) : ShapeWithXY(name, x, y)
    class SquareWithShape(name: String, x: Float, y: Float, val length: Float) : ShapeWithXY(name, x, y)

    open class Shape(val name: String)
    class Circle(name: String, val x: Float, val y: Float, val radius: Float) : Shape(name)
    class Square(name: String, val x: Float, val y: Float, val length: Float) : Shape(name)
    class Line(name: String, val x1: Float, val y1: Float, val x2: Float, val y2: Float) : Shape(name)

    /**
     * 곱 타입은 타입을 구성하는 값들의 합이 전체를 의미하지 않기 때문에 else 구문을 반드시 작성해야 함
     */
    private fun getGirthLength(shape: Shape): Double = when (shape) {
        is Circle -> 2 * Math.PI * shape.radius
        is Square -> 4 * shape.length.toDouble()
        is Line -> {
            val x2 = (shape.x2 - shape.x1.toDouble()).pow(2.0)
            val y2 = (shape.y2 - shape.y1.toDouble()).pow(2.0)
            sqrt(x2 + y2)
        }
        else -> throw IllegalArgumentException()
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
