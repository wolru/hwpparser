package typo.converter

import typo.TypoConverterInterface

class EmptyBraceBoxConverter : TypoConverterInterface {
    override fun convert(text: String): String {
        val matches = Regex("(\\{ *)?box( *\\{[^}]*})( *})?", RegexOption.IGNORE_CASE).findAll(text).toList()
        if (matches.isEmpty()) {
            return text
        }
        var replacedText = text
        val stringBuilder = StringBuilder()
        matches.reversed().forEach {
            stringBuilder.clear()
            if (it.groupValues[1].isNotBlank()) {
                stringBuilder.append(it.groupValues[1])
                if (it.groupValues[3].isBlank()) {
                    stringBuilder.append("{")
                }
            } else {
                stringBuilder.append("{")
            }
            stringBuilder.append("BOX").append(it.groupValues[2])
            if (it.groupValues[3].isNotBlank()) {
                stringBuilder.append(it.groupValues[3])
                if (it.groupValues[1].isBlank()) {
                    stringBuilder.append("}")
                }
            } else {
                stringBuilder.append("}")
            }
            replacedText = replacedText.replaceRange(it.range.first, it.range.last + 1, stringBuilder.toString())
        }
        return replacedText
    }
}