package typo.converter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EmptyBraceBoxConverterTest {

    private val emptyBraceBoxConverter = EmptyBraceBoxConverter()

    @Test
    fun convertTest() {
        var text = "box{~`~}"
        var convertedText = emptyBraceBoxConverter.convert(text)
        assertEquals("{BOX{~`~}}", convertedText)

        text = "{box{~`~}}"
        convertedText = emptyBraceBoxConverter.convert(text)
        assertEquals("{BOX{~`~}}", convertedText)

        text = "{ box{~`~} }"
        convertedText = emptyBraceBoxConverter.convert(text)
        assertEquals("{ BOX{~`~} }", convertedText)

        text = "{1 times box{~`~}}"
        convertedText = emptyBraceBoxConverter.convert(text)
        assertEquals("{1 times {BOX{~`~}}}", convertedText)

        text = "{1 times box{~`~} times 3}"
        convertedText = emptyBraceBoxConverter.convert(text)
        assertEquals("{1 times {BOX{~`~}} times 3}", convertedText)

        text = "{box{~`~} times 3}"
        convertedText = emptyBraceBoxConverter.convert(text)
        assertEquals("{{BOX{~`~}} times 3}", convertedText)
    }
}