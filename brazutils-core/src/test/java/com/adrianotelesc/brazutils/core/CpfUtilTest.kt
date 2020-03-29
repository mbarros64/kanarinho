package com.adrianotelesc.brazutils.core

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CpfUtilTest {

    private val util = CpfUtil()

    @Nested
    inner class IsValidCpf {
        @Test
        fun `given empty cpf, when verified if it's valid, then return false`() {
            val cpf = ""

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given blank cpf, when verified if it's valid, then return false`() {
            val cpf = " "

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given invalid formatted cpf, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-10"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given valid formatted cpf, when verified if it's valid, then return true`() {
            val cpf = "123.456.789-09"

            val isValid = util.isValidCpf(cpf)

            assertTrue(isValid)
        }

        @Test
        fun `given bad formatted cpf, when verified if it's valid, then return false`() {
            val cpf = "123 456 789 09"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with less than 11 characters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-0"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with greater than 11 characters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-099"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with letters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-jk"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with special characters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-%$"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf plus letters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-09jk"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf plus special characters, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-09%$"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf with whitespaces, when verified if it's valid, then return false`() {
            val cpf = "123.456.789-  "

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given formatted cpf plus whitespaces, when verified if it's valid, then return false`() {
            val cpf = "123.456.781-09 "

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given invalid unformatted cpf, when verified if it's valid, then return false`() {
            val cpf = "12345678910"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given valid unformatted cpf, when verified if it's valid, then return true`() {
            val cpf = "12345678909"

            val isValid = util.isValidCpf(cpf)

            assertTrue(isValid)
        }

        @Test
        fun `given unformatted cpf with less than 11 characters, when verified if it's valid, then return false`() {
            val cpf = "1234567890"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf with greater than 11 characters, when verified if it's valid, then return false`() {
            val cpf = "123456789099"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf with letters, when verified if it's valid, then return false`() {
            val cpf = "123456789jk"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf with special characters, when verified if it's valid, then return false`() {
            val cpf = "123456789%$"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf plus letters, when verified if it's valid, then return false`() {
            val cpf = "12345678909jk"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf plus special characters, when verified if it's valid, then return false`() {
            val cpf = "12345678909%$"

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf with whitespaces, when verified if it's valid, then return false`() {
            val cpf = "123456789  "

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }

        @Test
        fun `given unformatted cpf plus whitespaces, when verified if it's valid, then return false`() {
            val cpf = "12345678909 "

            val isValid = util.isValidCpf(cpf)

            assertFalse(isValid)
        }
    }

}