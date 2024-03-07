package domain.operation

class DivideOperation : Operation {

    override fun compute(leftOperand: Double, rightOperand: Double): Double {
        return leftOperand / rightOperand
    }
}