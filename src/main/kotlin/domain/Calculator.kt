package domain

import model.Expression
import model.Operator

class Calculator(
    private val expressionParser: ExpressionParser,
) {

    fun parse(input: String): Expression {
        return expressionParser.parse(input)
    }

    fun calculate(expression: Expression): Double {
        return expression.terms.sumOf { term ->
            when (term.operator) {
                Operator.Add -> AddOperation.compute(term)
                Operator.Subtract -> SubtractOperation.compute(term)
                Operator.Multiply -> MultiplyOperation.compute(term)
                Operator.Divide -> DivideOperation.compute(term)
                Operator.Remainder -> RemainderOperation.compute(term)
            }
        }
    }
}