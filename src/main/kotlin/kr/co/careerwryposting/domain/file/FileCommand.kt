package kr.co.careerwryposting.domain.file

import org.springframework.web.multipart.MultipartFile

data class FileCommand(
    val data: MultipartFile,
) {
    companion object {
        fun of(file: MultipartFile): FileCommand {
            return FileCommand(
                data = file
            )
        }
    }
}