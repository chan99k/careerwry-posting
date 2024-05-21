package kr.co.careerwryposting.common.response

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String,
) {
    COMMON_SYSTEM_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요"), // 장애 상황
    COMMON_INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "요청한 값이 올바르지 않습니다"),
    POST_INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "필수적인 JSON Property를 찾을 수 없습니다"),
    POST_NO_RESULTS(HttpStatus.NOT_FOUND, "검색 결과가 존재하지 않습니다"),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 게시물을 찾을 수 없습니다"),
}
