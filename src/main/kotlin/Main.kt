import kr.dogfoot.hwplib.reader.HWPReader
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod
import kr.dogfoot.hwplib.tool.textextractor.TextExtractOption
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor
import typo.PostCorrectionConverter
import typo.TypoConverter
import typo.TypoConverterInterface
import java.nio.file.Paths
import kotlin.io.path.*

fun main(args: Array<String>) {
    val hwpFile = HWPReader.fromFile("${Paths.get("src", "main", "resources").toAbsolutePath()}/rpm.hwp")
    val textExtractOption = TextExtractOption(TextExtractMethod.InsertControlTextBetweenParagraphText).apply {
        isInsertTag = true
    }
    with(Paths.get("hwp")) {
        if (notExists()) {
            createDirectory()
        }
    }
    var hwpText = TextExtractor.extract(hwpFile, textExtractOption)
    hwpText = ImageGenerator().generate(hwpFile, hwpText)
    hwpText = TypoConverter().convert(hwpText)
    hwpText = LatexConverter().convert(hwpText)
    hwpText = PostCorrectionConverter().convert(hwpText)
    HtmlGenerator().generate(hwpText)
}