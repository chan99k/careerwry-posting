package kr.co.careerwryposting.common.response

import io.github.oshai.kotlinlogging.KotlinLogging
import kr.co.careerwryposting.common.exeption.BaseException
import kr.co.careerwryposting.common.interceptor.CommonHttpRequestInterceptor
import org.apache.catalina.connector.ClientAbortException
import org.slf4j.MDC
import org.springframework.core.NestedExceptionUtils
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.resource.NoResourceFoundException

private val log = KotlinLogging.logger {}

@ControllerAdvice
class CommonControllerAdvice {
    /**
     * http status: 500 AND result: FAIL
     * 사전에 정의하지 않은 예외 발생
     * 시스템 예외 상황. 집중 모니터링 대상
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = [Throwable::class])
    fun onException(e: Throwable?): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.error { "eventId = $eventId, errorMessage = ${e!!.message}" }
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR.message, ErrorCode.COMMON_SYSTEM_ERROR.status)
    }
    fun nullJsonBodyException(){
        TODO("JSON 바디가 있어야 하는 데 없는 경우->500 에러를 던지고 있음")
    }

    fun invalidDataByForcedCommitException(){
        TODO("데이터 베이스를 직접 조작한 데이터가 정합성이 안 맞을 때, 꺼내오면서 500 에러가 발생하고 있음")
    }
    /**
     * http status: 200 AND result: FAIL
     * 시스템은 이슈 없고, 비즈니스 로직 처리에서 에러가 발생함
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = [BaseException::class])
    fun onBaseException(e: BaseException): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        if (SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST.contains(e.errorCode)) {
            log.error {
                "[BaseException]" +
                    " eventId = $eventId," +
                    " cause = ${NestedExceptionUtils.getMostSpecificCause(e)}," +
                    " errorMsg = ${NestedExceptionUtils.getMostSpecificCause(e).message}"
            }
        } else {
            log.warn {
                "[BaseException]" +
                    " eventId = $eventId," +
                    " cause = ${NestedExceptionUtils.getMostSpecificCause(e)}," +
                    " errorMsg = ${NestedExceptionUtils.getMostSpecificCause(e).message}"
            }
        }
        return CommonResponse.fail(e.message!!, e.errorCode!!.status)
    }

    /**
     * 예상치 않은 Exception 중에서 모니터링 skip 이 가능한 Exception 을 처리할 때
     * ex) ClientAbortException
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = [ClientAbortException::class])
    fun skipException(e: Throwable): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.warn {
            "[skipException]" +
                " eventId = $eventId," +
                " cause = ${NestedExceptionUtils.getMostSpecificCause(e)}," +
                " errorMsg = ${
                NestedExceptionUtils.getMostSpecificCause(e).message
                }"
        }
        return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR.status)
    }

    /**
     * http status: 400 AND result: FAIL
     * request parameter 에러
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.warn { "[BaseException] eventId = $eventId, errorMsg = ${NestedExceptionUtils.getMostSpecificCause(e).message}" }
        val bindingResult = e.bindingResult
        val fe = bindingResult.fieldError
        if (fe != null) {
            val message =
                "[Request Error]" + " " + fe.field + ": " + fe.rejectedValue + " -> (" + fe.defaultMessage + ")"
            return CommonResponse.fail(message, ErrorCode.COMMON_INVALID_PARAMETER.status)
        } else {
            return CommonResponse.fail(
                ErrorCode.COMMON_INVALID_PARAMETER.message,
                ErrorCode.COMMON_INVALID_PARAMETER.status
            )
        }
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = [HttpMessageNotReadableException::class])
    fun httpMessageNotReadableExceptionHandler(e: HttpMessageNotReadableException): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.warn { "[BaseException] eventId = $eventId, errorMsg = ${NestedExceptionUtils.getMostSpecificCause(e).message}" }

        return CommonResponse.fail(
            ErrorCode.POST_INVALID_PARAMETER.message,
            ErrorCode.POST_INVALID_PARAMETER.status
        )
    }

    // TODO :: 특별한 예외 페이지로 리다이렉션 시켜야 함
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = [NoResourceFoundException::class])
    fun noResourceFoundExceptionHandler(e: NoResourceFoundException): CommonResponse<*> {
        val eventId = MDC.get(CommonHttpRequestInterceptor.HEADER_REQUEST_UUID_KEY)
        log.warn { "[BaseException] eventId = $eventId, errorMsg = ${NestedExceptionUtils.getMostSpecificCause(e).message}" }

        return CommonResponse.fail(
            ErrorCode.COMMON_RESOURCE_NOT_FOUND.message,
            ErrorCode.COMMON_RESOURCE_NOT_FOUND.status
        )
    }

    companion object {
        private val SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST: List<ErrorCode> = mutableListOf()
    }
}
