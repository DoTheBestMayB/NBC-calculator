import controller.CalculatorController
import domain.Calculator
import domain.operation.*
import presentation.IOHandler

fun main() {
    val calculator = createCalculator()
    val ioHandler = IOHandler()
    val calculatorController = CalculatorController(ioHandler, calculator)

    calculatorController.start()
}

private fun createCalculator(): Calculator {
    val addOperation = AddOperation()
    val subtractOperation = SubtractOperation()
    val multiplyOperation = MultiplyOperation()
    val divideOperation = DivideOperation()
    val remainderOperation = RemainderOperation()
    return Calculator(
        addOperation,
        subtractOperation,
        multiplyOperation,
        divideOperation,
        remainderOperation,
    )
}