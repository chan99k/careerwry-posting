package kr.co.careerwryposting.domain.file

import io.awspring.cloud.s3.S3Operations
import kr.co.careerwryposting.common.util.AwsS3Properties
import kr.co.careerwryposting.common.util.TokenGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException


@Service
class S3Service(
    val s3Operations: S3Operations,

    @Autowired
    private val s3Properties: AwsS3Properties
) {
    @Throws(IOException::class)
    fun uploadFile(file: MultipartFile): String {
        val fileName: String = TokenGenerator.randomCharacterWithPrefix("File_") + file.originalFilename

        file.inputStream.use { inputStream ->
            s3Operations.upload(s3Properties.bucket, fileName, inputStream)
        }
        return "https://${s3Properties.bucket}.s3.amazonaws.com/$fileName" // S3 URL 생성
    }
}