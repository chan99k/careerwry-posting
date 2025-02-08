package kr.co.careerwryposting.interfaces.file

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.web.multipart.MultipartFile


class FileDto {
    data class PostImageRequest(
        @JsonProperty(value = "image-files")
        val files: Array<MultipartFile>,
        val postToken: String,
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as PostImageRequest

            return files.contentEquals(other.files)
        }

        override fun hashCode(): Int {
            return files.contentHashCode()
        }
    }
}