package kr.co.careerwryposting.interfaces.file

import kr.co.careerwryposting.application.file.FileFacade
import kr.co.careerwryposting.common.response.CommonResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile


@RestController
class FileApiController(
    private val fileFacade: FileFacade,
) {
    @PostMapping("/upload-files")
    fun uploadFiles(@RequestParam("image-files") files: Array<MultipartFile>): CommonResponse<*> {
        val postImageRequest = FileDto.PostImageRequest(files);
        fileFacade.save(postImageRequest);

        return CommonResponse.success("Files uploaded successfully", HttpStatus.OK)
    }
}