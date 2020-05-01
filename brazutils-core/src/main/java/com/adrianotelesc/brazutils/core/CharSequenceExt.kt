package com.adrianotelesc.brazutils.core

fun CharSequence.notMatchesAny(vararg regex: Regex) = !regex.any { matches(it) }

fun CharSequence.removeNotDigits() = replace(Regex("""\D"""), "")

fun CharSequence.hasRepeatedDigits(numberOfRepeatedDigits: Int = 1) =
    contains(Regex("""^(\d)\1*{$numberOfRepeatedDigits}$"""))

fun String.digits() = map(Character::getNumericValue)