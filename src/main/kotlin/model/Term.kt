package model

/**
 * 단일 계산식 클래스 입니다.
 * @property leftOperand 좌항 값
 * @property operator 연산자
 * @property rightOperand 우항 값
 */
data class Term(
    val leftOperand: Double,
    val operator: Operator,
    val rightOperand: Double,
)
