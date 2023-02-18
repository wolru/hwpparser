import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class LatexConverterTest {

    private val latexConverter = LatexConverter()

    @Test
    fun convertTest() {
        var convertedText = latexConverter.convert("<equation>\\textrm {A}(a,`` 3), ``\\textrm {B}(2, ``b)</equation>")
        println("convertedText = $convertedText")
    }
}