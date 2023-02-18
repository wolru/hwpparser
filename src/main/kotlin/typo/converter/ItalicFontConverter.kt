package typo.converter

import typo.TypoConverterInterface

class ItalicFontConverter : TypoConverterInterface {
    override fun convert(text: String): String {
        val matches = Regex("([^a-zA-Z])(it *)", RegexOption.IGNORE_CASE).findAll(text).toList()
        if (matches.isEmpty()) {
            return text
        }
        var replacedText = text
        matches.reversed().forEach {
            replacedText = replacedText.replaceRange(it.range.first, it.range.last + 1, "${it.groupValues[1]}")
        }
        return replacedText
    }
}