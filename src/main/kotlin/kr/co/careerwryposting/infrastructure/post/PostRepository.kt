package kr.co.careerwryposting.infrastructure.post

import kr.co.careerwryposting.domain.post.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>, PostQuerydslRepository {

    fun findByToken(token: String): Post?

    fun deleteByToken(token: String)
}
