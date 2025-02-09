package kr.co.careerwryposting.domain.file

interface FileWriter {
    fun save(file: FileCommand)
}