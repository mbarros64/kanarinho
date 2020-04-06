package com.adrianotelesc.brazutils.core

object CpfNumberUtil {

    fun isValidCnpj(cnpjNumber: String): Boolean {
        if (cnpjNumber.notContainsAny(FORMATTED_CNPJ_REGEX, UNFORMATTED_CNPJ_REGEX)) return false

        val cnpjDigitsOnly =
            if (cnpjNumber.matches(FORMATTED_CNPJ_REGEX)) cnpjNumber.removeNotDigits() else cnpjNumber

        if (cnpjDigitsOnly.hasRepeatedDigits(numberOfRepeatedDigits = 10)) return false

        val firstNineDigits = cnpjDigitsOnly.take(12)
        val validFirstCheckDigit = calculateCnpjCheckDigitBy(firstNineDigits)

        val firstCheckDigit = cnpjDigitsOnly[FIRST_CHECK_DIGIT_CNPJ_INDEX].toNumericValue()

        if (firstCheckDigit != validFirstCheckDigit) return false

        val firstTenDigits = cnpjDigitsOnly.take(13)
        val validSecondCheckDigit = calculateCnpjCheckDigitBy(firstTenDigits)

        val secondCheckDigit = cnpjDigitsOnly[SECOND_CHECK_DIGIT_CNPJ_INDEX].toNumericValue()

        if (secondCheckDigit != validSecondCheckDigit) return false

        return true
    }

    private fun calculateCnpjCheckDigitBy(cpfDigits: String): Int {
        if (cpfDigits.length !in 12..13)
            throw IllegalArgumentException("Cannot calculate the check digit correctly")

        val weights = arrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2).reversed()

        val reversedCpfDigits = cpfDigits.reversed()
        val sum = reversedCpfDigits.sumIndexedBy { index, digit ->
            val number = digit.toNumericValue()
            val weight = weights[index]

            number * weight
        }

        val mod11 = sum % 11
        return if (mod11 < 2) 0 else 11 - mod11
    }

    fun isValidCpf(cpfNumber: String): Boolean {
        if (cpfNumber.notContainsAny(FORMATTED_CPF_REGEX, UNFORMATTED_CPF_REGEX)) return false

        val cpfDigitsOnly =
            if (cpfNumber.matches(FORMATTED_CPF_REGEX)) cpfNumber.removeNotDigits() else cpfNumber

        if (cpfDigitsOnly.hasRepeatedDigits(numberOfRepeatedDigits = 10)) return false

        val firstNineDigits = cpfDigitsOnly.take(9)
        val validFirstCheckDigit = calculateCpfCheckDigitBy(firstNineDigits)

        val firstCheckDigit = cpfDigitsOnly[FIRST_CHECK_DIGIT_INDEX].toNumericValue()

        if (firstCheckDigit != validFirstCheckDigit) return false

        val firstTenDigits = cpfDigitsOnly.take(10)
        val validSecondCheckDigit = calculateCpfCheckDigitBy(firstTenDigits)

        val secondCheckDigit = cpfDigitsOnly[SECOND_CHECK_DIGIT_INDEX].toNumericValue()

        if (secondCheckDigit != validSecondCheckDigit) return false

        return true
    }

    private fun calculateCpfCheckDigitBy(cpfDigits: String): Int {
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

    private val FORMATTED_CPF_REGEX = Regex("""^\d{3}.\d{3}.\d{3}-\d{2}$""")
    private val FORMATTED_CNPJ_REGEX = Regex("""^\d{2}.\d{3}.\d{3}/\d{4}-\d{2}$""")
    private val UNFORMATTED_CPF_REGEX = Regex("""^\d{11}$""")
    private val UNFORMATTED_CNPJ_REGEX = Regex("""^\d{14}$""")
    private const val FIRST_CHECK_DIGIT_INDEX = 9
    private const val SECOND_CHECK_DIGIT_INDEX = 10
    private const val FIRST_CHECK_DIGIT_CNPJ_INDEX = 12
    private const val SECOND_CHECK_DIGIT_CNPJ_INDEX = 13
}
