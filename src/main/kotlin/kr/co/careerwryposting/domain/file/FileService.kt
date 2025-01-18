package kr.co.careerwryposting.domain.file

interface FileService {
    fun uploadFile(file: FileCommand)
    fun save(file: FileCommand)
}