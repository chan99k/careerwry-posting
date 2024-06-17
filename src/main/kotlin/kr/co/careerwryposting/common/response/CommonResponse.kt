package kr.co.careerwryposting.common.response

import org.springframework.http.HttpStatus

data class CommonResponse<T>(
    val result: Result,
    val data: T? = null,
    val message: String? = null,
    val status: HttpStatus = HttpStatus.OK
) {

    companion object {
        fun <T> success(data: T, message: String? = null): CommonResponse<T> {
            return CommonResponse(
                result = Result.SUCCESS,
                data = data,
                message = message
            )
        }

        fun <T> success(data: T, status: HttpStatus): CommonResponse<T> {
            return CommonResponse(
                result = Result.SUCCESS,
                data = data,
                status = status
            )
        }

        fun fail(message: String, status: HttpStatus): CommonResponse<Nothing> {
            return CommonResponse(
                result = Result.FAIL,
                message = message,
                status = status
            )
        }

        fun fail(errorCode: HttpStatus): CommonResponse<Nothing> {
            return CommonResponse(
                result = Result.FAIL,
                status = errorCode
            )
        }
    }

    enum class Result {
        SUCCESS, FAIL
    }
}
