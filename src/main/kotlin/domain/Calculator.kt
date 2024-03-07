package domain

import domain.operation.*
import model.Operator
import java.util.*

class Calculator(
    private val addOperation: AddOperation,
    private val subtractOperation: SubtractOperation,
    private val multiplyOperation: MultiplyOperation,
    private val divideOperation: DivideOperation,
    private val remainderOperation: RemainderOperation,
) {

    private val operator = Operator.entries.map { it.symbol.toString() }

    fun calculate(s: String): Double {
        if (s.isBlank()) {
            return 0.0
        }

        val stack = Stack<String>()
        val numSb = StringBuilder()
        var operation = '+'

        for (currentChar in s) {
            if (currentChar.isDigit()) {
                numSb.append(currentChar)
            } else if (currentChar == '(') {
                stack.push(operation.toString())
                operation = '+'
            } else if (!currentChar.isWhitespace()) {
                val num = evaluate(operation, numSb, stack)
                stack.push(num.toString())
                numSb.clear()
                operation = currentChar

                if (currentChar == ')') {
                    var term = 0.0
                    while (stack.peek() !in operator) {
                        term += stack.pop().toDouble()
                    }
                    numSb.append(term)
                    operation = stack.pop()[0]
                }
            }
        }

        val num = evaluate(operation, numSb, stack)
        stack.push(num.toString())

        var result = 0.0
        while (stack.isNotEmpty()) {
            result += stack.pop().toDouble()
        }
        return result
    }

    private fun evaluate(operation: Char, numSb: StringBuilder, stack: Stack<String>): Double {
        return when (operation) {
            Operator.Add.symbol -> addOperation.compute(0.0, numSb.toString().toDouble())
            Operator.Subtract.symbol -> subtractOperation.compute(0.0, numSb.toString().toDouble())
            Operator.Multiply.symbol -> multiplyOperation.compute(stack.pop().toDouble(), numSb.toString().toDouble())
            Operator.Divide.symbol -> divideOperation.compute(stack.pop().toDouble(), numSb.toString().toDouble())
            Operator.Remainder.symbol -> remainderOperation.compute(stack.pop().toDouble(), numSb.toString().toDouble())
            else -> throw IllegalArgumentException("지원하지 않는 연산자입니다.")
        }
    }
}