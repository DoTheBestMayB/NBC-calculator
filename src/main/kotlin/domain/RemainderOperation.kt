package domain

import model.Operator
import model.Term

data object RemainderOperation : Operation {
    override val symbol: Operator
        get() = Operator.Remainder

    override fun compute(term: Term): Double {
        checkOperatorIsSame(term)

        return term.leftOperand % term.rightOperand
    }
}