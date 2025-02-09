package kr.co.careerwryposting.domain.file

import org.springframework.web.multipart.MultipartFile

data class FileCommand(
    val file: MultipartFile,
    val postToken: String,
) {
    companion object {
        fun of(file: MultipartFile, postToken: String): FileCommand {
            return FileCommand(
                file = file,
                postToken = postToken,
            )
        }
    }
}