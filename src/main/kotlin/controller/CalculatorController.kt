package controller

import domain.Calculator
import domain.InputValidator
import model.MessageCategory
import presentation.IOHandler

class CalculatorController(
    private val ioHandler: IOHandler,
    private val calculator: Calculator,
    private val inputValidator: InputValidator,
) {
    fun start() {
        // 사용자에게 입력을 받는다.
        var input = getInput()

        while (input != null) {
            // 입력받은 문자열 유효성 검사
            val modifiedInput = inputValidator.checkValidAndModify(input)
            if (modifiedInput == null) {
                ioHandler.show(MessageCategory.INPUT_IS_NOT_VALID)
                input = getInput()
                continue
            }

            // 계산식을 연산하여 결괏값을 구한다.
            val result = calculator.calculate(modifiedInput)

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