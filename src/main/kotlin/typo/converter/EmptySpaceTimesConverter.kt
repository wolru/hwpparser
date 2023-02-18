package typo.converter

import typo.TypoConverterInterface

class EmptySpaceTimesConverter : TypoConverterInterface {
    override fun convert(text: String): String {
        val matches = Regex("([0-9]*)times([0-9]*)", RegexOption.IGNORE_CASE).findAll(text).toList()
        if (matches.isEmpty()) {
            return text
        }
        var replacedText = text
        val stringBuilder = StringBuilder()
        matches.reversed().forEach {
            stringBuilder.clear()
            if (it.groupValues[1].isNotBlank()) {
                stringBuilder.append(it.groupValues[1]).append(" ")
            }
            stringBuilder.append("times")
            if (it.groupValues[2].isNotBlank()) {
                stringBuilder.append(" ").append(it.groupValues[2])
            }
            replacedText = replacedText.replaceRange(it.range.first, it.range.last + 1, stringBuilder.toString())
        }
        return replacedText
    }
}