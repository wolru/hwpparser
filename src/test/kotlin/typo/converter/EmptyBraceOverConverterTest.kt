package typo.converter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EmptyBraceOverConverterTest {

    private val emptyBraceOverConverter = EmptyBraceOverConverter()

    @Test
    fun convertTest() {
        val text = "1over2"
        val convertedText = emptyBraceOverConverter.convert(text)
        assertEquals("{1}over{2}", convertedText)
    }

    @Test
    fun convertPowTest() {
        val text = "1over2 ^{3}"
        val convertedText = emptyBraceOverConverter.convert(text)
        assertEquals("{1}over{2 ^{3}}", convertedText)
    }
}