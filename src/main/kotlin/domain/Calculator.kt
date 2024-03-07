package domain

import model.Expression
import model.Operator

class Calculator(
    private val expressionParser: ExpressionParser,
    private val addOperation: AddOperation,
    private val subtractOperation: SubtractOperation,
    private val multiplyOperation: MultiplyOperation,
    private val divideOperation: DivideOperation,
    private val remainderOperation: RemainderOperation,
) {

    fun parse(input: String): Expression {
        return expressionParser.parse(input)
    }

    fun calculate(expression: Expression): Double {
        return expression.terms.sumOf { term ->
            when (term.operator) {
                Operator.Add -> addOperation.compute(term)
                Operator.Subtract -> subtractOperation.compute(term)
                Operator.Multiply -> multiplyOperation.compute(term)
                Operator.Divide -> divideOperation.compute(term)
                Operator.Remainder -> remainderOperation.compute(term)
            }
        }
    }
}