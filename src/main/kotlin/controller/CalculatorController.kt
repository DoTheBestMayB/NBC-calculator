package controller

import model.MessageCategory
import domain.Calculator
import presentation.IOHandler

class CalculatorController(
    private val ioHandler: IOHandler,
    private val calculator: Calculator,
) {
    fun start() {
        // 사용자에게 입력을 받는다.
        var input = getInput()

        while (input != null) {
            // 입력받은 문자열을 계산식으로 변환한다.
            val expression = calculator.parse(input)

            // 계산식을 연산하여 결괏값을 구한다.
            val result = calculator.calculate(expression)

            // 사용자에게 결괏값을 보여준다.
            ioHandler.showResult(result)

            // 사용자에게 입력을 받는다.
            input = getInput()
        }
        // 프로그램을 종료한다.
        finish()
        return
    }

    private fun getInput(): String? {
        ioHandler.show(MessageCategory.GET_INPUT)
        return ioHandler.getInput()
    }

    private fun finish() {
        ioHandler.show(MessageCategory.FINISH)
    }
}