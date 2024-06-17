package kr.co.careerwryposting.domain.comment

interface CommentWriter {
    fun save(command: CommentCommand): CommentInfo
    fun update(comment: Comment, request: CommentCommand): CommentInfo
    fun delete(comment: Comment)
}
