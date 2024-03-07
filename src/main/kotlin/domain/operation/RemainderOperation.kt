package domain.operation

class RemainderOperation : Operation {

    override fun compute(leftOperand: Double, rightOperand: Double): Double {

        return leftOperand % rightOperand
    }
}