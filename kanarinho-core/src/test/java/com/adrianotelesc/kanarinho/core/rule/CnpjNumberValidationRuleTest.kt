package com.adrianotelesc.kanarinho.core.rule

import com.adrianotelesc.kanarinho.core.Validator
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class CnpjNumberValidationRuleTest {

    @Test
    fun `given empty cnpj, when verified if it's valid, then return false`() {
        val cnpj = ""

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given blank cnpj, when verified if it's valid, then return false`() {
        val cnpj = " "

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given invalid formatted cnpj, when verified if it's valid, then return false`() {
        val cnpj = "11.222.333/0001-10"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given valid formatted cnpj, when verified if it's valid, then return true`() {
        val cnpj = "11.222.333/0001-81"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertTrue(isValid)
    }

    @Test
    fun `given bad formatted cnpj, when verified if it's valid, then return false`() {
        val cnpj = "11 222 333 0001 81"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given formatted cnpj with less than 11 characters, when verified if it's valid, then return false`() {
        val cnpj = "11.222.333/0001-8"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given formatted cnpj with greater than 11 characters, when verified if it's valid, then return false`() {
        val cnpj = "11.222.333/0001-109"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given formatted cnpj with letters, when verified if it's valid, then return false`() {
        val cnpj = "11.222.333/0001-jk"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given formatted cnpj with special characters, when verified if it's valid, then return false`() {
        val cnpj = "123.456.789-%$"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given formatted cnpj plus letters, when verified if it's valid, then return false`() {
        val cnpj = "11.222.333/0001-81jk"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given formatted cnpj plus special characters, when verified if it's valid, then return false`() {
        val cnpj = "11.222.333/0001-81%$"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given formatted cnpj with whitespaces, when verified if it's valid, then return false`() {
        val cnpj = "11.222.333/0001-  "

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given formatted cnpj plus whitespaces, when verified if it's valid, then return false`() {
        val cnpj = "11.222.333/0001-81 "

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given invalid unformatted cnpj, when verified if it's valid, then return false`() {
        val cnpj = "11222333000110"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given valid unformatted cnpj, when verified if it's valid, then return true`() {
        val cnpj = "11222333000181"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertTrue(isValid)
    }

    @Test
    fun `given unformatted cnpj with less than 11 characters, when verified if it's valid, then return false`() {
        val cnpj = "1234567890"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given unformatted cnpj with greater than 11 characters, when verified if it's valid, then return false`() {
        val cnpj = "112223330001819"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given unformatted cnpj with letters, when verified if it's valid, then return false`() {
        val cnpj = "112223330001jk"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given unformatted cnpj with special characters, when verified if it's valid, then return false`() {
        val cnpj = "112223330001%$"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given unformatted cnpj with repeated digits, when verified if it's valid, then return false`() {
        val cnpj = "11111111111111"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given unformatted cnpj plus letters, when verified if it's valid, then return false`() {
        val cnpj = "11222333000181jk"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given unformatted cnpj plus special characters, when verified if it's valid, then return false`() {
        val cnpj = "11222333000181%$"

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given unformatted cnpj with whitespaces, when verified if it's valid, then return false`() {
        val cnpj = "112223330001  "

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

    @Test
    fun `given unformatted cnpj plus whitespaces, when verified if it's valid, then return false`() {
        val cnpj = "11222333000181 "

        val isValid = Validator.validate(cnpj, CnpjNumberValidationRule())

        assertFalse(isValid)
    }

}