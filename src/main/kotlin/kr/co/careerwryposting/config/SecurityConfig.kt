package kr.co.careerwryposting.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/swagger-ui/**").permitAll() // swagger-ui 경로에 대한 접근은 인증 없이 허용
                    .anyRequest().permitAll() // 그 외의 모든 요청
            }
            .cors {
                it.configurationSource(corsConfigurationSource())
            }

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): UrlBasedCorsConfigurationSource {
        val corsConfig = CorsConfiguration()
        corsConfig.allowedOrigins = listOf("*") // 모든 출처 허용
        corsConfig.allowedMethods = listOf("*") // 모든 HTTP 메서드 허용
        corsConfig.allowedHeaders = listOf("*") // 모든 헤더 허용

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)
        return source
    }
}
