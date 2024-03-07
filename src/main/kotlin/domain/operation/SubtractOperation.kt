package domain.operation

class SubtractOperation : AbstractOperation() {
    override fun compute(leftOperand: Double, rightOperand: Double): Double {
        return leftOperand - rightOperand
    }
}