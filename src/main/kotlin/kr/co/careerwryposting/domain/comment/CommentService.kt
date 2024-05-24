package kr.co.careerwryposting.domain.comment

interface CommentService {
    fun addComment(command: CommentCommand): String
    fun updateComment(command: CommentCommand): String
    fun deleteComment(command: CommentCommand)
}