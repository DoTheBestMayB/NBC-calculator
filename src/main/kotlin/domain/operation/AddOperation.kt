package domain.operation

class AddOperation : AbstractOperation() {

    override fun compute(leftOperand: Double, rightOperand: Double): Double {
        return leftOperand + rightOperand
    }
}