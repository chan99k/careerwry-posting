package kr.co.careerwryposting.infrastructure.file

import kr.co.careerwryposting.domain.file.File
import kr.co.careerwryposting.domain.file.FileCommand
import kr.co.careerwryposting.domain.file.FileWriter
import org.springframework.stereotype.Repository

@Repository
class FileWriterImpl(
    private val fileRepository: FileRepository,
) : FileWriter {
    override fun save(fileCommmad: FileCommand) {
        fileRepository.save(fileCommmad)
        TODO("Not yet implemented")
    }


}