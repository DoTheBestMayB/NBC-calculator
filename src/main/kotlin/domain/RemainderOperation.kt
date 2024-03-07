package domain

import model.Operator
import model.Term

class RemainderOperation : Operation {
    override val symbol: Operator
        get() = Operator.Remainder

    override fun compute(term: Term): Double {
        checkOperatorIsSame(term)

        return term.leftOperand % term.rightOperand
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}