package typo

import typo.converter.*

class TypoConverter {
    private val converters = listOf(
        SingleBraceDotConverter(),
        EmptySpaceTimesConverter(),
        EmptyBraceOverConverter(),
        EmptyBraceBoxConverter(),
        ItalicFontConverter(),
    )

    fun convert(text: String): String {
        var checkedText = text
        converters.forEach {
            checkedText = it.convert(checkedText)
        }
        return checkedText
    }
}