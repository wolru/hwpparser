import kr.dogfoot.hwplib.reader.HWPReader
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod
import kr.dogfoot.hwplib.tool.textextractor.TextExtractOption
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Paths

fun main(args: Array<String>) {
    val resourceDirectory = Paths.get("src", "main", "resources").toAbsolutePath()
    val latexConverter = LatexConverter();
    val equationRegex = Regex("<equation>([^><]*)</equation>")
    val dotSingleBraceRegex = Regex("[^{](dot\\{[0-9+]})")

    val hwpFile = HWPReader.fromFile("/Users/hanbitkim/Downloads/RPM_01_A2.hwp")
    val imageIds = hwpFile.docInfo.binDataList.map {
        it.binDataID
    }
    val imageFiles = hwpFile.binData.embeddedBinaryDataList.sortedBy { it.name }.map {
        File("/Users/hanbitkim/hwp/${it.name}").apply {
            writeBytes(it.data)
        }
    }
    val textExtractOption = TextExtractOption(TextExtractMethod.InsertControlTextBetweenParagraphText).apply {
        isInsertTag = true
    }
    var hwpText = TextExtractor.extract(hwpFile, textExtractOption)
    imageFiles.forEachIndexed { index, file ->
        hwpText = hwpText.replace("<img src=${index + 1}>", "<img src=${file.name}>")
    }

    dotSingleBraceRegex.findAll(hwpText).toList().reversed().forEach {
        hwpText = hwpText.replaceRange(it.range.first, it.range.last + 1, "{${it.groupValues[0]}}")
    }

    equationRegex.findAll(hwpText).toList().reversed().forEach {
        val converted = latexConverter.convertToLatex(it.groupValues[1])
        hwpText = hwpText.replaceRange(it.range.first, it.range.last + 1, "\\($converted\\)")
    }

    val htmlFile = File("$resourceDirectory/template.html")
    BufferedReader(FileReader(htmlFile)).use { br ->
        val template = br.readLines().toList().joinToString("\n")
        val html = template.replace("<contents/>", hwpText)
        File("/Users/hanbitkim/hwp/index.html").apply {
            writeText(html)
        }
    }
}