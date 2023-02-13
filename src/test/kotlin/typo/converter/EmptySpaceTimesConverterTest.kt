package typo.converter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EmptySpaceTimesConverterTest {

    private val emptySpaceTimesConverter = EmptySpaceTimesConverter()

    @Test
    fun convertTest() {
        var text = "1times2"
        var convertedText = emptySpaceTimesConverter.convert(text)
        assertEquals("1 times 2", convertedText)

        text = "1 times2"
        convertedText = emptySpaceTimesConverter.convert(text)
        assertEquals("1 times 2", convertedText)

        text = "1times 2"
        convertedText = emptySpaceTimesConverter.convert(text)
        assertEquals("1 times 2", convertedText)

        text = "1 times 2"
        convertedText = emptySpaceTimesConverter.convert(text)
        assertEquals("1 times 2", convertedText)

        text = "{1times2 ^{2}}"
        convertedText = emptySpaceTimesConverter.convert(text)
        assertEquals("{1 times 2 ^{2}}", convertedText)
    }
}