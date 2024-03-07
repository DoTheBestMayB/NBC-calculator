package domain.operation

class AddOperation : Operation {

    override fun compute(leftOperand: Double, rightOperand: Double): Double {
        return leftOperand + rightOperand
    }
}