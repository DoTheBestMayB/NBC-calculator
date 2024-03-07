package domain.operation

class RemainderOperation : AbstractOperation() {

    override fun compute(leftOperand: Double, rightOperand: Double): Double {

        return leftOperand % rightOperand
    }
}