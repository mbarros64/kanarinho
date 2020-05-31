package com.adrianotelesc.kanarinho

import com.adrianotelesc.kanarinho.core.formatToCnpj
import com.adrianotelesc.kanarinho.core.formatToCpf
import com.adrianotelesc.kanarinho.core.formatToCpfOrCnpj
import com.adrianotelesc.kanarinho.core.removeNotDigits

class CpfCpnjFormattingTextWatcher(
    private var format: Format = Format.CPF_OR_CNPJ
) : BaseFormattingTextWatcher() {
    enum class Format { CPF, CNPJ, CPF_OR_CNPJ }

    override fun format(text: String): String {
        val satinized = text.removeNotDigits()

        return when (format) {
            Format.CPF -> satinized.formatToCpf()
            Format.CNPJ -> satinized.formatToCnpj()
            Format.CPF_OR_CNPJ -> satinized.formatToCpfOrCnpj()
        }
    }

}