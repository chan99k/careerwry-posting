package kr.co.careerwryposting.infrastructure.comment

import kr.co.careerwryposting.domain.comment.Comment
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface CommentQuerydslRepository {
    fun getCommentsByPostId(postId: Long, pageable: Pageable): Slice<Comment>
}