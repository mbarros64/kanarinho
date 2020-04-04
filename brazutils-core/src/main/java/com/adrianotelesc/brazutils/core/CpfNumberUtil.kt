package com.adrianotelesc.brazutils.core

object CpfNumberUtil {

    fun isValidCpf(cpfNumber: String): Boolean {
        if (cpfNumber.notContainsAny(FORMATTED_CPF_REGEX, UNFORMATTED_CPF_REGEX)) return false

        val cpfDigitsOnly =
            if (cpfNumber.matches(FORMATTED_CPF_REGEX)) cpfNumber.removeNotDigits() else cpfNumber

        if (cpfDigitsOnly.hasRepeatedDigits(numberOfRepeatedDigits = 10)) return false

        val firstNineDigits = cpfDigitsOnly.take(9)
        val validFirstCheckDigit = calculateCheckDigitBy(firstNineDigits)

        val firstCheckDigit = cpfDigitsOnly[FIRST_CHECK_DIGIT_INDEX].toNumericValue()

        if (firstCheckDigit != validFirstCheckDigit) return false

        val firstTenDigits = cpfDigitsOnly.take(10)
        val validSecondCheckDigit = calculateCheckDigitBy(firstTenDigits)

        val secondCheckDigit = cpfDigitsOnly[SECOND_CHECK_DIGIT_INDEX].toNumericValue()

        if (secondCheckDigit != validSecondCheckDigit) return false

        return true
    }

    private fun calculateCheckDigitBy(cpfDigits: String): Int {
        if (cpfDigits.length !in 9..10)
            throw IllegalArgumentException("Cannot calculate the check digit correctly")

        val reversedCpfDigits = cpfDigits.reversed()
        val sum = reversedCpfDigits.sumIndexedBy { index, digit ->
            val number = digit.toNumericValue()
            val weight = 2 + index

            number * weight
        }

        val mod11 = sum % 11
        return if (mod11 < 2) 0 else 11 - mod11
    }

    private val FORMATTED_CPF_REGEX = Regex("""^\d{3}.\d{3}.\d{3}-\d{2}${'$'}""")
    private val UNFORMATTED_CPF_REGEX = Regex("""^\d{11}$""")
    private const val FIRST_CHECK_DIGIT_INDEX = 9
    private const val SECOND_CHECK_DIGIT_INDEX = 10
}
