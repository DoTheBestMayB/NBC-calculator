package domain

import domain.operation.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

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

    @DisplayName("20+30을 입력하면 50을 반환한다")
    @Test
    fun `add solo test`() {
        // given
        val input = "20+30"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = 50.0
        assertThat(actual).isEqualTo(expected)
    }

    @DisplayName("200-312을 입력하면 -112을 반환한다")
    @Test
    fun `subtract solo test`() {
        // given
        val input = "200-312"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = -112.0
        assertThat(actual).isEqualTo(expected)
    }

    @DisplayName("20*3을 입력하면 60을 반환한다")
    @Test
    fun `multiply solo test`() {
        // given
        val input = "20*3"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = 60.0
        assertThat(actual).isEqualTo(expected)
    }

    @DisplayName("21/4를 입력하면 5.25를 반환한다")
    @Test
    fun `divide solo test`() {
        // given
        val input = "21/4"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = 5.25
        assertThat(actual).isEqualTo(expected)
    }

    @DisplayName("26%7을 입력하면 5를 반환한다")
    @Test
    fun `modulo solo test`() {
        // given
        val input = "26%7"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = 5.0
        assertThat(actual).isEqualTo(expected)
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

    @DisplayName("-2.3+(3.4*2)+0.2를 입력하면 4.7을 반환한다")
    @Test
    fun `정상적인 소숫점 연산식을 입력하면 계산식을 반환한다`() {
        // given
        val input = "0-2.3+(3.4*2)+0.2"

        // when
        val actual = calculator.calculate(input)

        // then
        val expected = 4.7
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("createInputAboutBracketsWithAnswer")
    @DisplayName("정상적인 괄호 연산식을 입력하면 연산 결과를 변환한다")
    fun `test for input with brackets`(data: Pair<String, Double>) {
        // given
        val (input, expected) = data

        // when
        val actual = calculator.calculate(input)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun createInputAboutBracketsWithAnswer(): List<Pair<String, Double>> {
            return listOf(
                "(1.4)" to 1.4,
                "(1)" to 1.0,
            )
        }
    }
}