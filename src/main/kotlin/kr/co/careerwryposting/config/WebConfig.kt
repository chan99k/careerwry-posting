package kr.co.careerwryposting.config

import kr.co.careerwryposting.common.interceptor.CommonHttpRequestInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val commonInterceptor: CommonHttpRequestInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(commonInterceptor)
            .addPathPatterns("/**")
    }
}
