package latex

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Paths
import kotlin.streams.toList

class LatexConverter {
    private val resourceDirectory = Paths.get("src", "main", "resources").toAbsolutePath()
    private val equationRegex = Regex("<equation>([^><]*)</equation>")

    fun convert(text: String): String {
        var convertedText = text
        equationRegex.findAll(text).toList().reversed().forEach {
            val converted = convertToLatex(it.groupValues[1])
            convertedText = convertedText.replaceRange(it.range.first, it.range.last + 1, "\\($converted\\)")
        }
        return convertedText
    }

    private fun convertToLatex(equation: String): String {
        val processBuilder = ProcessBuilder("python3", "$resourceDirectory/latex.py", equation).apply {
            redirectErrorStream(true)
        }
        val process = processBuilder.start()
        BufferedReader(InputStreamReader(process.inputStream)).use { br ->
            val latex = br.lines().toList().joinToString(separator = "")
            process.waitFor()
            return latex
        }
    }
}