package kr.co.careerwryposting

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
class CareerwryPostingApplication

fun main(args: Array<String>) {
    runApplication<CareerwryPostingApplication>(*args)
}
