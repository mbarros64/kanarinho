package com.adrianotelesc.kanarinho.core

fun String.isValidCpfNumber(): Boolean {
    if (this.notMatchesAny(FORMATTED_CPF_REGEX, UNFORMATTED_CPF_REGEX)) return false

    val sanitizedInput = this.removeNotDigits()

    if (sanitizedInput.hasRepeatedDigits(MAXIMUM_REPEATED_CPF_DIGITS)) return false

    fun mod11CheckDigit(digits: List<Int>): Int {
        require(digits.size in 9..10)

        val mod11 = digits.reversed().mapIndexed { index, digit ->
            val weight = index + 2
            digit * weight
        }.sum() % 11
        return if (mod11 in 0..1) 0 else 11 - mod11
    }

    val actualFirstCheckDigit = sanitizedInput[FIRST_CHECK_DIGIT_CPF_INDEX].toNumericValue()
    val expectedFirstCheckDigit = sanitizedInput.take(9).digits().let { mod11CheckDigit(it) }

    if (actualFirstCheckDigit != expectedFirstCheckDigit) return false

    val actualSecondCheckDigit = sanitizedInput[SECOND_CHECK_DIGIT_CPF_INDEX].toNumericValue()
    val expectedSecondCheckDigit = sanitizedInput.take(10).digits().let { mod11CheckDigit(it) }

    if (actualSecondCheckDigit != expectedSecondCheckDigit) return false

    return true
}

private val FORMATTED_CPF_REGEX = Regex("""^\d{3}.\d{3}.\d{3}-\d{2}$""")
private val UNFORMATTED_CPF_REGEX = Regex("""^\d{11}$""")

private const val MAXIMUM_REPEATED_CPF_DIGITS = 10

private const val FIRST_CHECK_DIGIT_CPF_INDEX = 9
private const val SECOND_CHECK_DIGIT_CPF_INDEX = 10

fun String.isValidCnpjNumber(): Boolean {
    if (this.notMatchesAny(FORMATTED_CNPJ_REGEX, UNFORMATTED_CNPJ_REGEX)) return false

    val sanitizedInput = this.removeNotDigits()

    if (sanitizedInput.hasRepeatedDigits(MAXIMUM_REPEATED_DIGITS)) return false

    fun mod11CheckDigit(digits: List<Int>): Int {
        require(digits.size in 12..13)

        val mod11 = digits.reversed().mapIndexed { index, digit ->
            val weight = if (index > 7) (index + 4) % 10 else index + 2
            digit * weight
        }.sum() % 11
        return if (mod11 in 0..1) 0 else 11 - mod11
    }

    val actualFirstCheckDigit = sanitizedInput[FIRST_CHECK_DIGIT_INDEX].toNumericValue()
    val expectedFirstCheckDigit = sanitizedInput.take(12).digits().let { mod11CheckDigit(it) }

    if (actualFirstCheckDigit != expectedFirstCheckDigit) return false

    val actualSecondCheckDigit = sanitizedInput[SECOND_CHECK_DIGIT_INDEX].toNumericValue()
    val expectedSecondCheckDigit = sanitizedInput.take(13).digits().let { mod11CheckDigit(it) }

    if (actualSecondCheckDigit != expectedSecondCheckDigit) return false

    return true
}

private val FORMATTED_CNPJ_REGEX = Regex("""^\d{2}.\d{3}.\d{3}/\d{4}-\d{2}$""")
private val UNFORMATTED_CNPJ_REGEX = Regex("""^\d{14}$""")

private const val MAXIMUM_REPEATED_DIGITS = 13

private const val FIRST_CHECK_DIGIT_INDEX = 12
private const val SECOND_CHECK_DIGIT_INDEX = 13