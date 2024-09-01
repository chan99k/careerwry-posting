package kr.co.careerwryposting.infrastructure.like

import kr.co.careerwryposting.domain.like.Like
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LikeRepository : JpaRepository<Like, Long> {
    fun findLikeByPostIdAndCreatedBy(postId: Long, createdBy: String): Like?
    @Query("SELECT COUNT(l) FROM Like l where l.post.id = :postId")
    fun countLikesByPostId(postId: Long): Long
}
