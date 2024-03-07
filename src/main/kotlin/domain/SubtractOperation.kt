package domain

import model.Operator
import model.Term

data object SubtractOperation : Operation {
    override val symbol: Operator
        get() = Operator.Subtract

    override fun compute(term: Term): Double {
        checkOperatorIsSame(term)

        return term.leftOperand - term.rightOperand
    }
}