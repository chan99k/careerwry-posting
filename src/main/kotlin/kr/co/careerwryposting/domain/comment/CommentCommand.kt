package kr.co.careerwryposting.domain.comment

import kr.co.careerwryposting.interfaces.comment.CommentDto

data class CommentCommand(
    val content: String?,
    val token: String
) {
    companion object {
        fun createCommand(request: CommentDto.CommentCreateRequest, postToken: String): CommentCommand {
            return CommentCommand(
                content = request.content,
                token = postToken
            )
        }

        fun updateCommand(request: CommentDto.CommentUpdateRequest, commentToken: String): CommentCommand {
            return CommentCommand(
                content = request.content,
                token = commentToken
            )
        }

        fun deleteCommand(commentToken: String): CommentCommand {
            return CommentCommand(
                content = null, // 삭제 요청에만 예외적으로 허용
                token = commentToken
            )
        }
    }
}
