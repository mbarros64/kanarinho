package com.adrianotelesc.brazutils.core

object CnpjNumberValidator {

    fun isValid(input: String): Boolean {
        if (input.notMatchesAny(FORMATTED_CNPJ_REGEX, UNFORMATTED_CNPJ_REGEX)) return false

        val sanitizedInput = input.removeNotDigits()

        if (sanitizedInput.hasRepeatedDigits(MAXIMUM_REPEATED_DIGITS)) return false

        val firstNineDigits = sanitizedInput.take(12).digits()
        val expectedFirstCheckDigit = calculateCheckDigit(firstNineDigits)

        val actualFirstCheckDigit = sanitizedInput[FIRST_CHECK_DIGIT_INDEX].toNumericValue()

        if (actualFirstCheckDigit != expectedFirstCheckDigit) return false

        val firstTenDigits = sanitizedInput.take(13).digits()
        val expectedSecondCheckDigit = calculateCheckDigit(firstTenDigits)

        val actualSecondCheckDigit = sanitizedInput[SECOND_CHECK_DIGIT_INDEX].toNumericValue()

        if (actualSecondCheckDigit != expectedSecondCheckDigit) return false

        return true
    }

    private fun calculateCheckDigit(digits: List<Int>): Int {
        require(digits.size in 12..13) { "Cannot calculate the cnpj check digit correctly" }

        val weights = arrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2).reversed()
        val reversedCnpjDigits = digits.reversed()

        val sum = reversedCnpjDigits.mapIndexed { index, digit -> digit * weights[index] }.sum()

        val mod11 = sum % 11
        return if (mod11 < 2) 0 else 11 - mod11
    }

    private val FORMATTED_CNPJ_REGEX = Regex("""^\d{2}.\d{3}.\d{3}/\d{4}-\d{2}$""")
    private val UNFORMATTED_CNPJ_REGEX = Regex("""^\d{14}$""")

    private const val MAXIMUM_REPEATED_DIGITS = 13

    private const val FIRST_CHECK_DIGIT_INDEX = 12
    private const val SECOND_CHECK_DIGIT_INDEX = 13

}