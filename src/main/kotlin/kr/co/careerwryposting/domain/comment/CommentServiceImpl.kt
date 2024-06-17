package kr.co.careerwryposting.domain.comment

import kr.co.careerwryposting.common.exeption.NotFoundException
import kr.co.careerwryposting.common.response.ErrorCode
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentWriter: CommentWriter,
    private val commentReader: CommentReader
) : CommentService {
    @Transactional
    override fun addComment(command: CommentCommand): CommentInfo {
        return commentWriter.save(command)
    }

    @Transactional(readOnly = true)
    override fun getCommentsByPostToken(postId: Long, pageable: Pageable): Slice<CommentInfo> {
        return commentReader.getCommentsByPostId(postId, pageable)
    }

    @Transactional
    override fun updateComment(command: CommentCommand): CommentInfo {
        val comment = commentReader.getComment(command.token)
            ?: throw NotFoundException(ErrorCode.COMMENT_NOT_FOUND)

        return commentWriter.update(comment = comment, request = command)
    }

    @Transactional
    override fun deleteComment(command: CommentCommand) {
        val comment = commentReader.getComment(command.token)
            ?: throw NotFoundException(ErrorCode.COMMENT_NOT_FOUND)

        return commentWriter.delete(comment)
    }
}
