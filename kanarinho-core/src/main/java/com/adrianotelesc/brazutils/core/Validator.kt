package com.adrianotelesc.kanarinho.core

import com.adrianotelesc.kanarinho.core.rule.ValidationRule

object Validator {
    fun <I> validate(input: I, vararg rules: ValidationRule<I>) = !rules.any { !it.validate(input) }
}