import kr.dogfoot.hwplib.reader.HWPReader
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod
import kr.dogfoot.hwplib.tool.textextractor.TextExtractOption
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor
import typo.TypoChecker

fun main(args: Array<String>) {
    val latexConverter = LatexConverter()
    val typoChecker = TypoChecker()
    val htmlGenerator = HtmlGenerator()
    val imageGenerator = ImageGenerator()

    val hwpFile = HWPReader.fromFile("/Users/hanbitkim/Downloads/RPM_01_A2.hwp")
    val textExtractOption = TextExtractOption(TextExtractMethod.InsertControlTextBetweenParagraphText).apply {
        isInsertTag = true
    }
    var hwpText = TextExtractor.extract(hwpFile, textExtractOption)
    hwpText = imageGenerator.generate(hwpFile, hwpText)
    hwpText = typoChecker.check(hwpText)
    hwpText = latexConverter.convert(hwpText)
    htmlGenerator.generate(hwpText)
}