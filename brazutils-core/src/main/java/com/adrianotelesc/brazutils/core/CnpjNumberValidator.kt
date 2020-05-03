package com.adrianotelesc.brazutils.core

object CnpjNumberValidator {

    fun isValid(input: String): Boolean {
        if (input.notMatchesAny(FORMATTED_CNPJ_REGEX, UNFORMATTED_CNPJ_REGEX)) return false

        val sanitizedInput = input.removeNotDigits()

        if (sanitizedInput.hasRepeatedDigits(MAXIMUM_REPEATED_DIGITS)) return false

        val actualFirstCheckDigit = sanitizedInput[FIRST_CHECK_DIGIT_INDEX].toNumericValue()
        val expectedFirstCheckDigit = sanitizedInput.take(12).digits().let { mod11CheckDigit(it) }

        if (actualFirstCheckDigit != expectedFirstCheckDigit) return false

        val actualSecondCheckDigit = sanitizedInput[SECOND_CHECK_DIGIT_INDEX].toNumericValue()
        val expectedSecondCheckDigit = sanitizedInput.take(13).digits().let { mod11CheckDigit(it) }

        if (actualSecondCheckDigit != expectedSecondCheckDigit) return false

        return true
    }

    private fun mod11CheckDigit(digits: List<Int>): Int {
        require(digits.size in 12..13)

        val mod11 = digits.reversed().mapIndexed { index, digit ->
            val weight = if (index > 7) (index + 4) % 10 else index + 2
            digit * weight
        }.sum() % 11
        return if (mod11 in 0..1) 0 else 11 - mod11
    }

    private val FORMATTED_CNPJ_REGEX = Regex("""^\d{2}.\d{3}.\d{3}/\d{4}-\d{2}$""")
    private val UNFORMATTED_CNPJ_REGEX = Regex("""^\d{14}$""")

    private const val MAXIMUM_REPEATED_DIGITS = 13

    private const val FIRST_CHECK_DIGIT_INDEX = 12
    private const val SECOND_CHECK_DIGIT_INDEX = 13

}