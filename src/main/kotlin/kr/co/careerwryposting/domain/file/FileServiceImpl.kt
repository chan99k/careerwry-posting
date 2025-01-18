package kr.co.careerwryposting.domain.file

import org.springframework.stereotype.Service

@Service
class FileServiceImpl(
    private val fileWriter: FileWriter,
) : FileService {
    override fun uploadFile(file: FileCommand) {
        fileWriter.save(file = file)
        TODO("Not yet implemented")
    }

    override fun save(file: FileCommand) {
        fileWriter.save(file)
        TODO("Not yet implemented")
    }
}