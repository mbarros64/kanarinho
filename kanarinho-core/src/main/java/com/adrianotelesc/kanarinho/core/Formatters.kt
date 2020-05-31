package com.adrianotelesc.kanarinho.core

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun String.formatToCpfOrCnpj() = if (length <= 11) formatToCpf() else formatToCnpj()

fun String.formatToCpf() = formatToMask("###.###.###-##")

fun String.formatToCnpj() = formatToMask("##.###.###/####-##")

fun BigDecimal.formatToBrazilianReal() =
    NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this).replace("$", "$ ")

fun String.formatToMask(mask: String): String {
    val stringBuilder = StringBuilder()

    val digits: String = removeNotDigits()
    if (digits.isNotEmpty()) {
        var digitIndex = 0
        for (maskIndex in mask.indices) {
            val charMascara: Char = mask[maskIndex]
            if (charMascara != '#') {
                stringBuilder.append(charMascara)
                continue
            }

            if (digitIndex >= digits.length) break

            stringBuilder.append(digits[digitIndex])
            digitIndex++
        }
    }

    return stringBuilder.toString()
}