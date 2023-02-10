import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Paths
import kotlin.streams.toList

class LatexConverter {
    private val resourceDirectory = Paths.get("src", "main", "resources").toAbsolutePath()

    fun convertToLatex(equation: String): String {
        val processBuilder = ProcessBuilder("python3", "$resourceDirectory/latex.py", equation).apply {
            redirectErrorStream(true)
        }
        val process = processBuilder.start()
        BufferedReader(InputStreamReader(process.inputStream, "euc-kr")).use { br ->
            val latex = br.lines().toList().joinToString(separator = "")
            process.waitFor()
            return latex
        }
    }
}