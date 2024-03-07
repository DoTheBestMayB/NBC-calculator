import domain.Calculator
import controller.CalculatorController
import domain.ExpressionParser
import presentation.IOHandler

fun main() {
    val calculator = createCalculator()
    val ioHandler = IOHandler()
    val calculatorController = CalculatorController(ioHandler, calculator)

    calculatorController.start()
}

private fun createCalculator() : Calculator {
    val expressionParser = ExpressionParser()
    return Calculator(expressionParser)
}