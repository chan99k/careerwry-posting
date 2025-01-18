package kr.co.careerwryposting.config

import jakarta.servlet.MultipartConfigElement
import org.springframework.boot.web.servlet.MultipartConfigFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.unit.DataSize
import org.springframework.web.multipart.MultipartResolver
import org.springframework.web.multipart.support.StandardServletMultipartResolver

@Configuration
class MultiPartFileConfig {
    @Bean
    fun multipartResolver(): MultipartResolver {
        return StandardServletMultipartResolver()
    }

    @Bean
    fun multipartConfigElement(): MultipartConfigElement {
        val factory = MultipartConfigFactory()
        factory.setMaxFileSize(DataSize.ofMegabytes(50))
        factory.setFileSizeThreshold(DataSize.ofMegabytes(25)) // 이 크기를 넘으면 디스크에 저장
        factory.setMaxRequestSize(DataSize.ofMegabytes(500))

        return factory.createMultipartConfig()
    }
}