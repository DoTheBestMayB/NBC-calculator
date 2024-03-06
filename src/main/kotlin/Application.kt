import domain.Calculator
import domain.CalculatorController
import domain.IOHandler

fun main() {
    val calculator = Calculator()
    val ioHandler = IOHandler()
    val calculatorController = CalculatorController(ioHandler, calculator)

    calculatorController.start()
}