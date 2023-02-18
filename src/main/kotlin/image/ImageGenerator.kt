package image

import kr.dogfoot.hwplib.`object`.HWPFile
import java.io.File
import java.nio.file.Paths

class ImageGenerator {
    fun generate(hwp: HWPFile, text: String): String {
        var convertedText = text
        val imageIds = hwp.docInfo.binDataList.map {
            it.binDataID
        }
        hwp.binData.embeddedBinaryDataList.sortedBy { it.name }.map {
            File("${Paths.get("hwp").toAbsolutePath()}/${it.name}").apply {
                writeBytes(it.data)
            }
        }.forEachIndexed { index, file ->
            convertedText = convertedText.replace("<img src=${index + 1}", "<img src=${file.name}")
        }
        return convertedText
    }
}