package domain

import domain.operation.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculatorTest {

    private lateinit var calculator: Calculator

    @BeforeEach
    fun setUp() {
        calculator = Calculator(
            addOperation = AddOperation(),
            subtractOperation = SubtractOperation(),
            multiplyOperation = MultiplyOperation(),
            divideOperation = DivideOperation(),
            remainderOperation = RemainderOperation()
        )
    }

    @Test
    fun `2+((56+2)*3)+2를 입력하면 178을 반환한다`() {
        // given
        val input = "2+((56+2)*3)+2"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = 178.0
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `2+(-3*2)+2를 입력하면 -2을 반환한다`() {
        // given
        val input = "2+(0-3*2)+2"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = -2.0
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `2+(+3*4)를 입력하면 14를 반환한다`() {
        // given
        val input = "2+(0+3*4)"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = 14.0
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `2+(-(+3*2)+2)를 입력하면 -2을 반환한다`() {
        // given
        val input = "2+(0-(0+3*2)+2)"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = -2.0
        assertThat(actual).isEqualTo(expected)
    }

}