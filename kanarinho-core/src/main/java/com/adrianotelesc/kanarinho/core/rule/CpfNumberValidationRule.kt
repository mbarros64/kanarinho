package com.adrianotelesc.kanarinho.core.rule

import com.adrianotelesc.kanarinho.core.*

class CpfNumberValidationRule : ValidationRule<String> {

    override fun validate(input: String?): Boolean {
        if (input == null) return false

        if (input.notMatchesAny(FORMATTED_CPF_REGEX, UNFORMATTED_CPF_REGEX)) return false

        val sanitizedInput = input.removeNotDigits()

        if (sanitizedInput.hasRepeatedDigits(MAXIMUM_REPEATED_DIGITS)) return false

        val actualFirstCheckDigit = sanitizedInput[FIRST_CHECK_DIGIT_INDEX].toNumericValue()
        val expectedFirstCheckDigit = sanitizedInput.take(9).digits().let { mod11CheckDigit(it) }

        if (actualFirstCheckDigit != expectedFirstCheckDigit) return false

        val actualSecondCheckDigit = sanitizedInput[SECOND_CHECK_DIGIT_INDEX].toNumericValue()
        val expectedSecondCheckDigit = sanitizedInput.take(10).digits().let { mod11CheckDigit(it) }

        if (actualSecondCheckDigit != expectedSecondCheckDigit) return false

        return true
    }

    private fun mod11CheckDigit(digits: List<Int>): Int {
        require(digits.size in 9..10)

        val mod11 = digits.reversed().mapIndexed { index, digit ->
            val weight = index + 2
            digit * weight
        }.sum() % 11
        return if (mod11 in 0..1) 0 else 11 - mod11
    }

    companion object {
        private val FORMATTED_CPF_REGEX = Regex("""^\d{3}.\d{3}.\d{3}-\d{2}$""")
        private val UNFORMATTED_CPF_REGEX = Regex("""^\d{11}$""")

        private const val MAXIMUM_REPEATED_DIGITS = 10

        private const val FIRST_CHECK_DIGIT_INDEX = 9
        private const val SECOND_CHECK_DIGIT_INDEX = 10
    }

}