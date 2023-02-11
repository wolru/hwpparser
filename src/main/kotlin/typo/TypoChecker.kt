package typo

import typo.converter.EmptyBraceOverConverter
import typo.converter.SingleBraceDotConverter
class TypoChecker {
    private val typoConverters = listOf(
        SingleBraceDotConverter(),
        EmptyBraceOverConverter()
    )

    fun check(text: String): String {
        var checkedText = text
        typoConverters.forEach {
            checkedText = it.convert(checkedText)
        }
        return checkedText
    }
}