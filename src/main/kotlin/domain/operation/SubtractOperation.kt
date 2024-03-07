package domain.operation

class SubtractOperation : Operation {
    override fun compute(leftOperand: Double, rightOperand: Double): Double {
        return leftOperand - rightOperand
    }
}