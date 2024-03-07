package domain.operation

class DivideOperation : AbstractOperation() {

    override fun compute(leftOperand: Double, rightOperand: Double): Double {
        return leftOperand / rightOperand
    }
}