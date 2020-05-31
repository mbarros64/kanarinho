package com.adrianotelesc.kanarinho

import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import com.adrianotelesc.kanarinho.core.removeAt

abstract class BaseFormattingTextWatcher : TextWatcher {

    private var beforeText = ""

    private var numberOfDigitsToRightOfCursor = 0

    private var selfChange = false

    private var removeAt: Int? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        if (selfChange) return

        beforeText = s.toString()
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (selfChange) return

        val initialCursorPosition = start + before
        numberOfDigitsToRightOfCursor = beforeText.length - initialCursorPosition

        val removedAt = start + count
        removeAt = if (before == 1 && beforeText.getOrNull(removedAt)?.isDigit() == false) {
            removedAt - 1
        } else {
            null
        }
    }

    @Synchronized
    override fun afterTextChanged(s: Editable) {
        if (selfChange) return

        val text = removeAt?.let { s.toString().removeAt(it) } ?: run { s.toString() }

        val formattedText = format(text)

        selfChange = true

        s.replace(0, s.length, formattedText, 0, formattedText.length)

        val newCursorPosition = formattedText.length - numberOfDigitsToRightOfCursor
        if (formattedText == s.toString()) Selection.setSelection(s, newCursorPosition)

        selfChange = false
    }

    abstract fun format(text: String): String

}
