package domain.operation

class MultiplyOperation : AbstractOperation() {

    override fun compute(leftOperand: Double, rightOperand: Double): Double {
        return leftOperand * rightOperand
    }
}