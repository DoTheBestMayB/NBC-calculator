package domain

import model.Operator
import model.Term

data object DivideOperation : Operation {
    override val symbol: Operator
        get() = Operator.Divide

    override fun compute(term: Term): Double {
        checkOperatorIsSame(term)

        return term.leftOperand / term.rightOperand
    }
}