package domain

import model.Operator
import model.Term

data object MultiplyOperation : Operation {

    override val symbol: Operator
        get() = Operator.Multiply

    override fun compute(term: Term): Double {
        checkOperatorIsSame(term)

        return term.leftOperand * term.rightOperand
    }
}