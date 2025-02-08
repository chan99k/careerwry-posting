package kr.co.careerwryposting.common.util

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "spring.cloud.aws.s3")
class AwsS3Properties {
    lateinit var bucket: String
    lateinit var accessKey: String
    lateinit var secretKey: String
}