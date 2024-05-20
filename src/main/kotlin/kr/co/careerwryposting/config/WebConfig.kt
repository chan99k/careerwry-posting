package kr.co.careerwryposting.config

import kr.co.careerwryposting.common.interceptor.CommonHttpRequestInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val commonInterceptor: CommonHttpRequestInterceptor,
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(commonInterceptor)
            .addPathPatterns("/**") //"/api/v1/"이 붙은 모든 URI에 대해 인터셉터를 적용시킨다는 의미
    }
}