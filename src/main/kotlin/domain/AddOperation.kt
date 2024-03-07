package domain

import model.Operator
import model.Term

data object AddOperation : Operation {

    override val symbol: Operator
        get() = Operator.Add

    override fun compute(term: Term): Double {
        checkOperatorIsSame(term)

        return term.leftOperand + term.rightOperand
    }
}