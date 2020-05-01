package com.adrianotelesc.brazutils.core

object CpfCnpjNumberValidator {

    fun isValidCnpj(input: String): Boolean {
        if (input.notMatchesAny(FORMATTED_CNPJ_REGEX, UNFORMATTED_CNPJ_REGEX)) return false

        val sanitizedInput = input.removeNotDigits()

        if (sanitizedInput.hasRepeatedDigits(MAXIMUM_REPEATED_CNPJ_DIGITS)) return false

        val firstNineDigits = sanitizedInput.take(12).digits()
        val expectedFirstCheckDigit = calculateCnpjCheckDigit(firstNineDigits)

        val actualFirstCheckDigit = sanitizedInput[FIRST_CHECK_DIGIT_CNPJ_INDEX].toNumericValue()

        if (actualFirstCheckDigit != expectedFirstCheckDigit) return false

        val firstTenDigits = sanitizedInput.take(13).digits()
        val expectedSecondCheckDigit = calculateCnpjCheckDigit(firstTenDigits)

        val actualSecondCheckDigit = sanitizedInput[SECOND_CHECK_DIGIT_CNPJ_INDEX].toNumericValue()

        if (actualSecondCheckDigit != expectedSecondCheckDigit) return false

        return true
    }

    private fun calculateCnpjCheckDigit(cnpjDigits: List<Int>): Int {
        require(cnpjDigits.size in 12..13) { "Cannot calculate the cnpj check digit correctly" }

        val weights = arrayOf(6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2).reversed()
        val reversedCpfDigits = cnpjDigits.reversed()

        val sum = reversedCpfDigits.mapIndexed { index, digit -> digit * weights[index] }.sum()

        val mod11 = sum % 11
        return if (mod11 < 2) 0 else 11 - mod11
    }

    fun isValidCpf(input: String): Boolean {
        if (input.notMatchesAny(FORMATTED_CPF_REGEX, UNFORMATTED_CPF_REGEX)) return false

        val sanitizedInput = input.removeNotDigits()

        if (sanitizedInput.hasRepeatedDigits(MAXIMUM_REPEATED_CPF_DIGITS)) return false

        val firstNineDigits = sanitizedInput.take(9).digits()
        val expectedFirstCheckDigit = calculateCpfCheckDigit(firstNineDigits)

        val actualFirstCheckDigit = sanitizedInput[FIRST_CHECK_DIGIT_INDEX].toNumericValue()

        if (actualFirstCheckDigit != expectedFirstCheckDigit) return false

        val firstTenDigits = sanitizedInput.take(10).digits()
        val validSecondCheckDigit = calculateCpfCheckDigit(firstTenDigits)

        val secondCheckDigit = sanitizedInput[SECOND_CHECK_DIGIT_INDEX].toNumericValue()

        if (secondCheckDigit != validSecondCheckDigit) return false

        return true
    }

    private fun calculateCpfCheckDigit(cpfDigits: List<Int>): Int {
        require(cpfDigits.size in 9..10) { "Cannot calculate the cpf check digit correctly" }

        val reversedCpfDigits = cpfDigits.reversed()

        val sum = reversedCpfDigits.mapIndexed { index, digit ->
            val weight = 2 + index

            digit * weight
        }.sum()

        val mod11 = sum % 11
        return if (mod11 < 2) 0 else 11 - mod11
    }

    private val FORMATTED_CPF_REGEX = Regex("""^\d{3}.\d{3}.\d{3}-\d{2}$""")
    private val FORMATTED_CNPJ_REGEX = Regex("""^\d{2}.\d{3}.\d{3}/\d{4}-\d{2}$""")
    private val UNFORMATTED_CPF_REGEX = Regex("""^\d{11}$""")
    private val UNFORMATTED_CNPJ_REGEX = Regex("""^\d{14}$""")

    private const val MAXIMUM_REPEATED_CPF_DIGITS = 10
    private const val MAXIMUM_REPEATED_CNPJ_DIGITS = 13

    private const val FIRST_CHECK_DIGIT_INDEX = 9
    private const val SECOND_CHECK_DIGIT_INDEX = 10

    private const val FIRST_CHECK_DIGIT_CNPJ_INDEX = 12
    private const val SECOND_CHECK_DIGIT_CNPJ_INDEX = 13

}