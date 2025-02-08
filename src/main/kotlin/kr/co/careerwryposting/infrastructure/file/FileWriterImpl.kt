package kr.co.careerwryposting.infrastructure.file

import kr.co.careerwryposting.common.exeption.NotFoundException
import kr.co.careerwryposting.domain.file.File
import kr.co.careerwryposting.domain.file.FileCommand
import kr.co.careerwryposting.domain.file.FileWriter
import kr.co.careerwryposting.domain.file.S3Service
import kr.co.careerwryposting.infrastructure.post.PostRepository
import org.springframework.stereotype.Repository

@Repository
class FileWriterImpl(
    private val fileRepository: FileRepository,
    private val s3Service: S3Service,
    private val postRepository: PostRepository,
) : FileWriter {
    override fun save(file: FileCommand) {
        val post = postRepository.findByToken(file.postToken) ?: throw NotFoundException()
        val s3Url = s3Service.uploadFile(file.file)

        fileRepository.save(
            File.fixture(
                post = post,
                file = file.file,
                url = s3Url,
            )
        )
    }


}