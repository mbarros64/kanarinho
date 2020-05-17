package com.adrianotelesc.kanarinho.core.rule

interface ValidationRule<I> {
    fun validate(input: I?): Boolean
}