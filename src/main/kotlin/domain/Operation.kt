package domain

import model.Operator
import model.Term

sealed interface Operation {

    val symbol: Operator

    fun compute(term: Term): Double

    fun checkOperatorIsSame(term: Term) {
        if (term.operator != symbol) {
            throw IllegalArgumentException()
        }
    }
}