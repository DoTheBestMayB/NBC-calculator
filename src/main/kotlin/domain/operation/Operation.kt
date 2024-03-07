package domain.operation

interface Operation {

    fun compute(leftOperand: Double, rightOperand: Double): Double
}