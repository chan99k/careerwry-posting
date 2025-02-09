package kr.co.careerwryposting.application.file

import kr.co.careerwryposting.domain.file.FileCommand
import kr.co.careerwryposting.domain.file.FileService
import kr.co.careerwryposting.interfaces.file.FileDto
import org.springframework.stereotype.Service

@Service
class FileFacade(
    private val fileService: FileService,
) {
    fun save(request: FileDto.PostImageRequest) {
        request.files.forEach { file ->
            fileService.save(FileCommand.of(file, request.postToken));
        }
    }
}