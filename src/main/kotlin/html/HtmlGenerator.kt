package html

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.file.Paths

class HtmlGenerator {
    private val resourceDirectory = Paths.get("src", "main", "resources").toAbsolutePath()

    fun generate(text: String) {
        val htmlFile = File("$resourceDirectory/template.html")
        BufferedReader(FileReader(htmlFile)).use { br ->
            val template = br.readLines().toList().joinToString("\n")
            val html = template.replace("<contents/>", text)
            File("${Paths.get("hwp").toAbsolutePath()}/index.html").apply {
                writeText(html)
            }
        }
    }
}