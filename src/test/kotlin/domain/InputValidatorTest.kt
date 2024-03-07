package domain

import org.junit.jupiter.api.BeforeEach
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class InputValidatorTest {

    private lateinit var validator: InputValidator

    @BeforeEach
    fun setUp() {
        validator = InputValidator()
    }

    @Test
    fun `소숫점을 규칙에 맞게 입력한 경우 보정된 문자열을 반환한다`() {
        // given
        val input = "-2.3+(3.4*2)+0.2"

        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = "0-2.3+(3.4*2)+0.2"
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["1..2+3", "1.2.+3", "1.2..3", "-1.3.", "-1.3..", "1+(.4+5)"])
    @DisplayName("소숫점을 규칙에 맞게 입력하지 않은 경우 null을 반환한다")
    fun `test for wrong decimal position`(input: String) {
        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = null
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["2.", "2+", "2-", "2/", "2*", "2%", "2)", "2("])
    @DisplayName("입력한 계산식의 끝이 올바르지 않은 경우 null을 리턴한다")
    fun `test for end of input`(input: String) {
        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = null
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `) 다음에 숫자가 오는 경우 null을 리턴한다`() {
        // given
        val input = "2+(65)67"

        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = null
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `첫 입력값으로 --가 연속으로 나올 경우 null을 리턴한다`() {
        // given
        val input = "--100"

        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = null
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `부호 다음에 )가 오는 경우 null을 리턴한다`() {
        // given
        val input = "2+(+)+2"

        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = null
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `숫자 다음에 (가 오는 경우 null을 리턴한다`() {
        // given
        val input = "2+(56(24))"

        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = null
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `공백을 포함하는 조건에 맞는 연산식을 입력하면 공백을 없앤 보정된 문자열을 반환한다`() {
        // given
        val input = "2 2 7 + 3      *(-3*2+2)-2    1 4"

        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = "227+3*(0-3*2+2)-214"
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("createInputAndModifiedOutput")
    fun `조건식에 맞는 연산식을 입력하면 null이 아닌 보정된 문자열을 반환한다`(data: Pair<String, String>) {
        // given
        val (input, expected) = data

        // when
        val actual = validator.checkValidAndModify(input)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {

        @JvmStatic
        fun createInputAndModifiedOutput(): List<Pair<String, String>> {
            return listOf(
                "2+((56+2)*3)+2" to "2+((56+2)*3)+2",
                "2+(-3*2)+2" to "2+(0-3*2)+2",
                "2+(+3*4)" to "2+(0+3*4)",
                "2+(-(+3*2)+2)" to "2+(0-(0+3*2)+2)",
            )
        }

    }
}