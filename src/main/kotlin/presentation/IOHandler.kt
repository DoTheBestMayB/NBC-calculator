package presentation

import model.MessageCategory

class IOHandler {

    fun getInput(): String? {
        val input = readlnOrNull()
        if (input.isNullOrBlank()) {
            return null
        }
        return input
    }

    fun show(messageCategory: MessageCategory) {
        when (messageCategory) {
            MessageCategory.FINISH -> println(END_PROGRAM)
            MessageCategory.GET_INPUT -> println(TYPE_INPUT)
            MessageCategory.INPUT_IS_NOT_VALID -> println(EXPRESSION_IS_NOT_CORRECT)
        }
    }

    fun showResult(result: Double) {
        println("$RESULT_IS $result")
    }

    companion object {
        private const val TYPE_INPUT = "연산식을 입력해주세요.(-1 또는 공백만 입력시 종료)"
        private const val EXPRESSION_IS_NOT_CORRECT = "연산식이 올바르지 않습니다."
        private const val RESULT_IS = "연산 결과 :"
        private const val END_PROGRAM = "계산기를 종료합니다."
    }
}