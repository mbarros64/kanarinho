package com.adrianotelesc.brazutils.core

fun CharSequence.notContainsAny(vararg regex: Regex) = !regex.any { contains(it) }

fun CharSequence.removeNotDigits() = replace(Regex("""\D"""), "")

fun CharSequence.hasRepeatedDigits(numberOfRepeatedDigits: Int = 1) =
    contains(Regex("""^(\d)\1*{$numberOfRepeatedDigits}$"""))

inline fun CharSequence.sumIndexedBy(selector: (index: Int, char: Char) -> Int): Int {
    var index = 0
    var sum = 0
    for (element in this) {
        sum += selector(index++, element)
    }
    return sum
}