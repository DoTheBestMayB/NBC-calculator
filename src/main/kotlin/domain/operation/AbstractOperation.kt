package domain.operation

abstract class AbstractOperation {

    abstract fun compute(leftOperand: Double, rightOperand: Double): Double
}