package domain

import model.Operator

class InputValidator {

    private val allowedNumber = ('0'..'9').toList()
    private val allowedOperator = Operator.entries.map { it.symbol }

    fun checkValidAndModify(input: String): String? {
        val sb = StringBuilder()
        var bracketNum = 0
        var beforeChar = ' '
        var correctionChar: Char? = null

        for (c in input) {
            when(c) {
                '(' -> {
                    if (beforeChar in allowedNumber) { // 숫자 다음에 (를 허용하지 않음
                        return null
                    }
                    bracketNum++
                }
                ')' -> {
                    if (bracketNum-- <= 0 || beforeChar in allowedOperator) {
                        return null
                    }
                }
                ' ' -> continue
                '+', '-' -> {
                    if (beforeChar in allowedOperator) { // 연속된 연산자 사용을 허용하지 않음
                        return null
                    } else if (beforeChar == '(') {
                        correctionChar = '0'
                    }
                }
                '/', '*', '%' -> {
                    if (beforeChar in allowedOperator) { // 연속된 연산자 사용을 허용하지 않음
                        return null
                    }
                }
                in allowedNumber -> {
                    if (beforeChar == ')') { // ) 다음에 숫자를 허용하지 않음
                        return null
                    }
                }
                else -> return null
            }
            beforeChar = c
            if (correctionChar != null) {
                sb.append(correctionChar)
                correctionChar = null
            }
            sb.append(c)
        }
        if (bracketNum != 0) {
            return null
        }
        return sb.toString()
    }
}