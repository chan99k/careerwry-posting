package kr.co.careerwryposting.domain.file

import org.springframework.stereotype.Service

@Service
class FileServiceImpl(
    private val fileWriter: FileWriter,
) : FileService {
    override fun save(file: FileCommand) {
        fileWriter.save(file)
    }
}