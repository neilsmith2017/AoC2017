package main

class Captcha {

    fun solveNextChar(code: String): Int {
        return if (code.length > 1) {
            compareCharacters(code, 1)
        } else {
            0
        }
    }

    fun solveHalfwayRound(code: String): Int {
        return compareCharacters(code, code.length / 2)
    }

    private fun compareCharacters(code: String, offset: Int): Int {
        var count = 0
        for (i in 0 until code.length) {
            if (code[i] == code[getComparisonCharacter(code.length, offset, i)]) {
                count += Character.getNumericValue(code[i])
            }
        }
        return count
    }

    private fun getComparisonCharacter(codeLength: Int, offsetFromCurrentPosition: Int, currentPosition: Int): Int {
        val nextPosition = offsetFromCurrentPosition + currentPosition

        return if (nextPosition >= codeLength) {
            nextPosition - codeLength
        } else {
            nextPosition
        }
    }
}