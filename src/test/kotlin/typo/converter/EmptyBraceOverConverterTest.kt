package typo.converter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EmptyBraceOverConverterTest {

    private val emptyBraceOverConverter = EmptyBraceOverConverter()

    @Test
    fun convertTest() {
        var text = "1over2"
        var convertedText = emptyBraceOverConverter.convert(text)
        assertEquals("{1}over{2}", convertedText)

        text = "1over2 ^{3}"
        convertedText = emptyBraceOverConverter.convert(text)
        assertEquals("{1}over{2 ^{3}}", convertedText)
    }
}