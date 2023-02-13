import kr.dogfoot.hwplib.reader.HWPReader
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod
import kr.dogfoot.hwplib.tool.textextractor.TextExtractOption
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor
import typo.TypoChecker
import java.nio.file.Paths
import kotlin.io.path.*

fun main(args: Array<String>) {
    val latexConverter = LatexConverter()
    val typoChecker = TypoChecker()
    val htmlGenerator = HtmlGenerator()
    val imageGenerator = ImageGenerator()

    val hwpFile = HWPReader.fromFile("${Paths.get("src", "main", "resources").toAbsolutePath()}/RPM_01_A2.hwp")
    val textExtractOption = TextExtractOption(TextExtractMethod.InsertControlTextBetweenParagraphText).apply {
        isInsertTag = true
    }
    with(Paths.get("hwp")) {
        if (notExists()) {
            createDirectory()
        }
    }
    var hwpText = TextExtractor.extract(hwpFile, textExtractOption)
    hwpText = imageGenerator.generate(hwpFile, hwpText)
    hwpText = typoChecker.check(hwpText)
    hwpText = latexConverter.convert(hwpText)
    htmlGenerator.generate(hwpText)
}