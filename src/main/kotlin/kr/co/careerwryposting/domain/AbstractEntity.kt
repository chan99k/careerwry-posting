package kr.co.careerwryposting.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AbstractEntity(
    @CreatedDate var createdDate: LocalDateTime? = null,
    @LastModifiedDate var lastModifiedDate: LocalDateTime? = null,
    @CreatedBy val createdBy: String? = null,
    @LastModifiedBy val lastModifiedBy: String? = null,
)