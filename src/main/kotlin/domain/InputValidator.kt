package domain

import model.Operator

class InputValidator {

    private val allowedNumber = ('0'..'9').toList()
    private val allowedOperator = Operator.entries.map { it.symbol }

    fun checkValidAndModify(input: String): String? {
        val sb = StringBuilder()
        var bracketNum = 0
        var beforeChar = ' '
        var isDotAppeared = false
        var correctionChar: Char? = null

        for (c in input) {
            when(c) {
                '(' -> {
                    if (beforeChar in allowedNumber || beforeChar == '.') { // 숫자 다음에 (를 허용하지 않음
                        return null
                    }
                    bracketNum++
                }
                ')' -> {
                    if (bracketNum-- <= 0 || beforeChar in allowedOperator || beforeChar == '.') {
                        return null
                    }
                }
                '.' -> {
                    if (isDotAppeared || beforeChar !in allowedNumber) {
                        return null
                    }
                    isDotAppeared = true
                }
                ' ' -> continue
                '+', '-' -> {
                    if (beforeChar in allowedOperator || beforeChar == '.') { // 연속된 연산자 사용을 허용하지 않음
                        return null
                    } else if (beforeChar == '(' || beforeChar == ' ') {
                        correctionChar = '0'
                    }
                    isDotAppeared = false
                }
                '/', '*', '%' -> {
                    if (beforeChar in allowedOperator || beforeChar == '.') { // 연속된 연산자 사용을 허용하지 않음
                        return null
                    }
                    isDotAppeared = false
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