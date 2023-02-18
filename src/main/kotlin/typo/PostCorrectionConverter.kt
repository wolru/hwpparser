package typo

import typo.converter.*

class PostCorrectionConverter {
    private val converters = listOf(
        RomanFontConverter(),
    )

    fun convert(text: String): String {
        var checkedText = text
        converters.forEach {
            checkedText = it.convert(checkedText)
        }
        return checkedText
    }
}