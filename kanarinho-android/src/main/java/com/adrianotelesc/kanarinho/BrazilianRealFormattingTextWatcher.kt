package com.adrianotelesc.kanarinho

import com.adrianotelesc.kanarinho.core.formatToBrazilianReal
import com.adrianotelesc.kanarinho.core.removeNotDigits
import java.math.BigDecimal

class BrazilianRealFormattingTextWatcher : BaseFormattingTextWatcher() {

    override fun format(text: String): String {
        val satinized = text.removeNotDigits()

        val amount = if (satinized.isNotEmpty()) {
            satinized.toBigDecimal().movePointLeft(2)
        } else {
            BigDecimal.ZERO
        }

        return amount.formatToBrazilianReal()
    }

}