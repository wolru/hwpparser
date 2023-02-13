package typo

import typo.converter.EmptyBraceBoxConverter
import typo.converter.EmptyBraceOverConverter
import typo.converter.EmptySpaceTimesConverter
import typo.converter.SingleBraceDotConverter
class TypoChecker {
    private val typoConverters = listOf(
        SingleBraceDotConverter(),
        EmptySpaceTimesConverter(),
        EmptyBraceOverConverter(),
        EmptyBraceBoxConverter(),
    )

    fun check(text: String): String {
        var checkedText = text
        typoConverters.forEach {
            checkedText = it.convert(checkedText)
        }
        return checkedText
    }
}