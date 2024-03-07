package domain

import model.Expression
import model.Operator
import java.util.*

class Calculator(
    private val addOperation: AddOperation,
    private val subtractOperation: SubtractOperation,
    private val multiplyOperation: MultiplyOperation,
    private val divideOperation: DivideOperation,
    private val remainderOperation: RemainderOperation,
) {

    private val operator = Operator.entries.map { it.symbol }

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
                when (operation) {
                    '+', '-' -> stack.push(evaluate(operation, numSb.toString(), "0"))
                    '*', '/', '%' -> stack.push(evaluate(operation, stack.pop(), numSb.toString()))
                }
                numSb.clear()
                operation = currentChar

                if (currentChar == ')') {
                    var term = 0
                    while (stack.peek() !in operator) {
                        term += stack.pop().toInt()
                    }

                    numSb.append(term)
                    operation = stack.pop()[0]
                }
            }
        }

        when (operation) {
            '+', '-' -> stack.push(evaluate(operation, numSb.toString(), "0"))
            '*', '/', '%' -> stack.push(evaluate(operation, stack.pop(), numSb.toString()))
        }

        var result = 0.0
        while (stack.isNotEmpty()) {
            result += stack.pop().toDouble()
        }
        return result
    }

    private fun evaluate(operator: Char, first: String, second: String): String {
        val x = first.toInt()
        val y = second.toInt()

        return when (operator) {
            '+' -> first
            '-' -> (-x).toString()
            '*' -> (x * y).toString()
            '/' -> (x / y).toString()
            '%' -> (x % y).toString()
            else -> throw IllegalArgumentException()
        }
    }
}