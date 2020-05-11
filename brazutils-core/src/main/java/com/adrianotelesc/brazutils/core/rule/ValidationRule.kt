package com.adrianotelesc.brazutils.core.rule

interface ValidationRule<I> {
    fun validate(input: I?): Boolean
}