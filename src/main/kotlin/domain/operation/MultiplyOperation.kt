package domain.operation

class MultiplyOperation : Operation {

    override fun compute(leftOperand: Double, rightOperand: Double): Double {
        return leftOperand * rightOperand
    }
}