package kr.co.careerwryposting.common.interceptor

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.servlet.HandlerInterceptor
import java.util.*

private val log = KotlinLogging.logger {}

@Component
class CommonHttpRequestInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        var requestEventId = request.getHeader(HEADER_REQUEST_UUID_KEY)
        if (!StringUtils.hasText(requestEventId)) {
            requestEventId = UUID.randomUUID().toString()
            log.info { "eventId = $requestEventId generated" }
        }

        MDC.put(HEADER_REQUEST_UUID_KEY, requestEventId)
        return true
    }

    companion object {
        const val HEADER_REQUEST_UUID_KEY: String = "x-request-id"
    }
}
