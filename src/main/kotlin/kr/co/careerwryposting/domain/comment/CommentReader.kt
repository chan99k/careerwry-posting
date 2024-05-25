package kr.co.careerwryposting.domain.comment

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface CommentReader {
    fun getComment(token: String): Comment?
    fun getCommentsByPostId(postId: Long, pageable: Pageable): Slice<CommentInfo>
}