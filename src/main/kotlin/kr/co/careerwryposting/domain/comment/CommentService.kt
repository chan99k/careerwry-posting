package kr.co.careerwryposting.domain.comment

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface CommentService {
    fun addComment(command: CommentCommand): CommentInfo
    fun updateComment(command: CommentCommand): CommentInfo
    fun deleteComment(command: CommentCommand)
    fun getCommentsByPostToken(postId: Long, pageable: Pageable): Slice<CommentInfo>
}
