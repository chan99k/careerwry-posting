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
    // 파일 업로드 기능을 테스트 하기 위한 엔드포인트
    @PostMapping("/upload-files")
    fun uploadFiles(
        @RequestParam("image-files") files: Array<MultipartFile>,
        @RequestParam("postToken") token: String
    ): CommonResponse<*> {
        val postImageRequest = FileDto.PostImageRequest(files,token);

        fileFacade.save(postImageRequest);

        return CommonResponse.success("Files uploaded successfully", HttpStatus.OK)
    }
}