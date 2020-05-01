package com.adrianotelesc.brazutils.core

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CpfCnpjNumberValidatorTest {

    @Nested
    inner class IsValidCpf {
        @Test
        fun `given empty cpf, when verified if it's valid, then return false`() {
            val cpf = ""

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given blank cpf, when verified if it's valid, then return false`() {
            val cpf = " "

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given invalid formatted cpf, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-10"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given valid formatted cpf, when verified if it's valid, then return true`() {
            val cpf = "123.456.789-09"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertTrue(isValid)
        }

        @Test
        fun `given bad formatted cpf, when verified if it's valid, then return false`() {
            val cpf = "123 456 789 09"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with less than 11 characters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-0"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with greater than 11 characters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-099"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with letters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-jk"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with special characters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-%$"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf plus letters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-09jk"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf plus special characters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-09%$"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with whitespaces, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-  "

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf plus whitespaces, when verified if it's valid, then return false`() {
            val cpf = "123.456.781-09 "

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given invalid unformatted cpf, when verified if it's valid, then return false`() {
            val cpf = "12345678910"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given valid unformatted cpf, when verified if it's valid, then return true`() {
            val cpf = "12345678909"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertTrue(isValid)
        }

        @Test
        fun `given unformatted cpf with less than 11 characters, when verified if it's valid, then return false`() {
            val cpf = "1234567890"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf with greater than 11 characters, when verified if it's valid, then return false`() {
            val cpf = "123456789099"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf with letters, when verified if it's valid, then return false`() {
            val cpf = "123456789jk"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf with special characters, when verified if it's valid, then return false`() {
            val cpf = "123456789%$"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf with repeated digits, when verified if it's valid, then return false`() {
            val cpf = "11111111111"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf plus letters, when verified if it's valid, then return false`() {
            val cpf = "12345678909jk"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf plus special characters, when verified if it's valid, then return false`() {
            val cpf = "12345678909%$"

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf with whitespaces, when verified if it's valid, then return false`() {
            val cpf = "123456789  "

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf plus whitespaces, when verified if it's valid, then return false`() {
            val cpf = "12345678909 "

            val isValid = CpfCnpjNumberValidator.isValidCpf(cpf)

            assertFalse(isValid)
        }
    }

    @Nested
    inner class IsValidCnpj {
        @Test
        fun `given empty cnpj, when verified if it's valid, then return false`() {
            val cnpj = ""

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given blank cnpj, when verified if it's valid, then return false`() {
            val cnpj = " "

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given invalid formatted cnpj, when verified if it's valid, then return false`() {
            val cnpj = "11.222.333/0001-10"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given valid formatted cnpj, when verified if it's valid, then return true`() {
            val cnpj = "11.222.333/0001-81"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertTrue(isValid)
        }

        @Test
        fun `given bad formatted cnpj, when verified if it's valid, then return false`() {
            val cnpj = "11 222 333 0001 81"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cnpj with less than 11 characters, when verified if it's valid, then return false`() {
            val cnpj = "11.222.333/0001-8"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cnpj with greater than 11 characters, when verified if it's valid, then return false`() {
            val cnpj = "11.222.333/0001-109"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cnpj with letters, when verified if it's valid, then return false`() {
            val cnpj = "11.222.333/0001-jk"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cnpj with special characters, when verified if it's valid, then return false`() {
            val cnpj = "123.456.789-%$"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cnpj plus letters, when verified if it's valid, then return false`() {
            val cnpj = "11.222.333/0001-81jk"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cnpj plus special characters, when verified if it's valid, then return false`() {
            val cnpj = "11.222.333/0001-81%$"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cnpj with whitespaces, when verified if it's valid, then return false`() {
            val cnpj = "11.222.333/0001-  "

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cnpj plus whitespaces, when verified if it's valid, then return false`() {
            val cnpj = "11.222.333/0001-81 "

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given invalid unformatted cnpj, when verified if it's valid, then return false`() {
            val cnpj = "11222333000110"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun  `given valid unformatted cnpj, when verified if it's valid, then return true`() {
            val cnpj = "11222333000181"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertTrue(isValid)
        }

        @Test
        fun `given unformatted cnpj with less than 11 characters, when verified if it's valid, then return false`() {
            val cnpj = "1234567890"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cnpj with greater than 11 characters, when verified if it's valid, then return false`() {
            val cnpj = "112223330001819"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cnpj with letters, when verified if it's valid, then return false`() {
            val cnpj = "112223330001jk"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cnpj with special characters, when verified if it's valid, then return false`() {
            val cnpj = "112223330001%$"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cnpj with repeated digits, when verified if it's valid, then return false`() {
            val cnpj = "11111111111111"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cnpj plus letters, when verified if it's valid, then return false`() {
            val cnpj = "11222333000181jk"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cnpj plus special characters, when verified if it's valid, then return false`() {
            val cnpj = "11222333000181%$"

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cnpj with whitespaces, when verified if it's valid, then return false`() {
            val cnpj = "112223330001  "

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cnpj plus whitespaces, when verified if it's valid, then return false`() {
            val cnpj = "11222333000181 "

            val isValid = CpfCnpjNumberValidator.isValidCnpj(cnpj)

            assertFalse(isValid)
        }
    }

}