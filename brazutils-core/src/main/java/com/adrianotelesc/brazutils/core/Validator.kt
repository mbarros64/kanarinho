package com.adrianotelesc.brazutils.core

import com.adrianotelesc.brazutils.core.rule.ValidationRule

object Validator {
    fun <I> validate(input: I, vararg rules: ValidationRule<I>) = !rules.any { !it.validate(input) }
}