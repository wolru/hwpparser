package typo.converter

import typo.TypoConverterInterface

class RomanFontConverter : TypoConverterInterface {
    override fun convert(text: String): String {
        val matches = Regex("(rm *[a-zA-Z]*)", RegexOption.IGNORE_CASE).findAll(text).toList()
        if (matches.isEmpty()) {
            return text
        }
        var replacedText = text
        matches.reversed().forEach {
            replacedText = replacedText.replaceRange(it.range.first, it.range.last + 1, "\\textrm {${it.groupValues[0].replace("rm", "").trim()}}")
        }
        return replacedText
    }
}