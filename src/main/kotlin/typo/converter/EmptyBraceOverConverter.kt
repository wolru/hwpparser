package typo.converter

import typo.TypoConverter

class EmptyBraceOverConverter : TypoConverter {
    override fun convert(text: String): String {
        val matches = Regex("([0-9]+)over([0-9]+( *\\^\\{[0-9]+})?)").findAll(text).toList()
        if (matches.isEmpty()) {
            return text
        }
        var replacedText = text
        matches.reversed().forEach {
            replacedText = replacedText.replaceRange(it.range.first, it.range.last + 1, "{${it.groupValues[1]}}over{${it.groupValues[2]}}")
        }
        return replacedText
    }
}