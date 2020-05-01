package com.adrianotelesc.brazutils.core

object CpfNumberValidator {

    fun isValid(input: String): Boolean {
        if (input.notMatchesAny(FORMATTED_CPF_REGEX, UNFORMATTED_CPF_REGEX)) return false

        val sanitizedInput = input.removeNotDigits()

        if (sanitizedInput.hasRepeatedDigits(MAXIMUM_REPEATED_DIGITS)) return false

        val firstNineDigits = sanitizedInput.take(9).digits()
        val expectedFirstCheckDigit = calculateCheckDigit(firstNineDigits)

        val actualFirstCheckDigit = sanitizedInput[FIRST_CHECK_DIGIT_INDEX].toNumericValue()

        if (actualFirstCheckDigit != expectedFirstCheckDigit) return false

        val firstTenDigits = sanitizedInput.take(10).digits()
        val validSecondCheckDigit = calculateCheckDigit(firstTenDigits)

        val secondCheckDigit = sanitizedInput[SECOND_CHECK_DIGIT_INDEX].toNumericValue()

        if (secondCheckDigit != validSecondCheckDigit) return false

        return true
    }

    private fun calculateCheckDigit(digits: List<Int>): Int {
        require(digits.size in 9..10) { "Cannot calculate the cpf check digit correctly" }

        val reversedCpfDigits = digits.reversed()

        val sum = reversedCpfDigits.mapIndexed { index, digit ->
            val weight = 2 + index

            digit * weight
        }.sum()

        val mod11 = sum % 11
        return if (mod11 < 2) 0 else 11 - mod11
    }

    private val FORMATTED_CPF_REGEX = Regex("""^\d{3}.\d{3}.\d{3}-\d{2}$""")
    private val UNFORMATTED_CPF_REGEX = Regex("""^\d{11}$""")

    private const val MAXIMUM_REPEATED_DIGITS = 10

    private const val FIRST_CHECK_DIGIT_INDEX = 9
    private const val SECOND_CHECK_DIGIT_INDEX = 10

}