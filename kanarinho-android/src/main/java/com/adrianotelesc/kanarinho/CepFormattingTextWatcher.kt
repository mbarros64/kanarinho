package com.adrianotelesc.kanarinho

import com.adrianotelesc.kanarinho.core.formatToMask
import com.adrianotelesc.kanarinho.core.removeNotDigits

class CepFormattingTextWatcher : BaseFormattingTextWatcher() {
    override fun format(text: String): String = text.removeNotDigits().formatToMask("#####-###")
}