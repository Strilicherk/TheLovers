package org.example.thelovers.core.domain

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberMask : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.filter { it.isDigit() }.take(11)

        val formatted = buildString {
            for (i in trimmed.indices) {
                when (i) {
                    0 -> append("(").append(trimmed[i])
                    1 -> append(trimmed[i]).append(") ")
                    6 -> append(trimmed[i]).append("-")
                    else -> append(trimmed[i])
                }
            }
        }

        return TransformedText(
            AnnotatedString(formatted),
            PhoneOffsetMapping()
        )
    }

    class PhoneOffsetMapping() : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            var transformedOffset = offset
            if (offset >= 1) transformedOffset += 1 // (
            if (offset >= 2) transformedOffset += 2 // )
            if (offset >= 7) transformedOffset += 1 // -
            return transformedOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            var originalOffset = offset
            if (offset > 0) originalOffset -= 1 // (
            if (offset > 3) originalOffset -= 2 // )
            if (offset > 9) originalOffset -= 1 // -
            return originalOffset.coerceAtLeast(0)
        }
    }
}


