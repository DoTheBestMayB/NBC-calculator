package model

/**
 * 파싱된 단일 계산식을 모아둔 클래스입니다.
 * 계산식 Term을 연산한 결과를 모두 더하면 사용자가 입력한 계산식의 결괏값을 얻을 수 있습니다.
 * @property terms 단일 계산식들의 list
 */
data class Expression(
    val terms: List<Term>
) {
    companion object {
        val empty = Expression(emptyList())
    }
}