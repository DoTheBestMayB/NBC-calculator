package domain

import org.junit.jupiter.api.BeforeEach
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputValidatorTest {

    private lateinit var validator: InputValidator

    @BeforeEach
    fun setUp() {
        validator = InputValidator()
    }

    @Test
    fun `) 다음에 숫자가 오는 경우 false를 리턴한다`() {
        // given
        val input = "2+(65)67"

        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = null
        assertThat(actual).isEqualTo(expected)
    }
    
    @Test
    fun `부호 다음에 )가 오는 경우 false를 리턴한다`() {
        // given
        val input = "2+(+)+2"
        
        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = null
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `숫자 다음에 (가 오는 경우 false를 리턴한다`() {
        // given
        val input = "2+(56(24))"

        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val expected = null
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @ValueSource(strings = ["2+((56+2)*3)+2", "2+(-3*2)+2", "2+(+3*4)", "2+(-(+3*2)+2)"])
    fun `조건식에 맞는 연산식을 입력하면 null이 아닌 보정된 문자열을 반환한다`(input: String) {
        // when
        val actual = validator.checkValidAndModify(input)

        // then
        val notExpected = null
        assertThat(actual).isNotEqualTo(notExpected)
    }
}