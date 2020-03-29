package com.adrianotelesc.brazutils.core

class CpfNumberUtil {
    fun isValidCpf(cpf: String): Boolean {
        if (!cpf.matches(FORMATTED_CPF_REGEX) && !cpf.matches(UNFORMATTED_CPF_REGEX))
            return false

        val unformattedCpf = cpf.replace(NON_DIGIT_REGEX, "")

        return unformattedCpf[9].toString().toInt() == calculateFirstCheckDigit(unformattedCpf) &&
                unformattedCpf[10].toString().toInt() == calculateSecondCheckDigit(unformattedCpf)
    }

    private fun calculateFirstCheckDigit(cpf: String): Int {
        val firstNine = cpf.substring(0, 9)
        var multiplier = 10
        val sum = firstNine.sumBy { char ->
            val digitValue = char.toString().toInt()
            digitValue * multiplier--
        }
        val mod11 = sum.rem(11)
        return if (mod11 in 0..1) 0 else 11 - mod11
    }

    private fun calculateSecondCheckDigit(cpf: String): Int {
        val firstTen = cpf.substring(0, 10)
        var multiplier = 11
        val sum = firstTen.sumBy { char ->
            val digitValue = char.toString().toInt()
            digitValue * multiplier--
        }
        val mod11 = sum.rem(11)
        return if (mod11 in 0..1) 0 else 11 - mod11
    }

    companion object {
        val FORMATTED_CPF_REGEX = Regex("""^\d{3}.\d{3}.\d{3}-\d{2}$""")
        val UNFORMATTED_CPF_REGEX = Regex("""^\d{11}$""")
        val NON_DIGIT_REGEX = Regex("""\D""")
    }
}
