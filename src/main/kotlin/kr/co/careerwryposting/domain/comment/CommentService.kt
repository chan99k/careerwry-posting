package kr.co.careerwryposting.domain.comment

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice

interface CommentService {
    fun addComment(command: CommentCommand): String
    fun updateComment(command: CommentCommand): String
    fun deleteComment(command: CommentCommand)
    fun getCommentsByPostToken(postId: Long, pageable: Pageable): Slice<CommentInfo>
}