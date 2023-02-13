package typo.converter

import typo.TypoConverter

class SingleBraceDotConverter : TypoConverter {
    override fun convert(text: String): String {
        val matches = Regex("[ *]?(dot\\{[0-9+]})").findAll(text).toList()
        if (matches.isEmpty()) {
            return text
        }
        var replacedText = text
        matches.reversed().forEach {
            replacedText = replacedText.replaceRange(it.range.first, it.range.last + 1, "{${it.groupValues[0]}}")
        }
        return replacedText
    }
}