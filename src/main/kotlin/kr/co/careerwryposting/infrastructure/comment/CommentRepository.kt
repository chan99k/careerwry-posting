package kr.co.careerwryposting.infrastructure.comment

import kr.co.careerwryposting.domain.comment.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByToken(token: String): Comment?
}