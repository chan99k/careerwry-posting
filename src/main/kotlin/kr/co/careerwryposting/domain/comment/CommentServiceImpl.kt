package kr.co.careerwryposting.domain.comment

import kr.co.careerwryposting.common.exeption.NotFoundException
import kr.co.careerwryposting.common.response.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentWriter: CommentWriter,
    private val commentReader: CommentReader,
) : CommentService {
    @Transactional
    override fun addComment(command: CommentCommand): String {
        return commentWriter.save(command).token
    }

    @Transactional
    override fun updateComment(command: CommentCommand): String {
        val comment = commentReader.getComment(command.token)
            ?: throw NotFoundException(ErrorCode.COMMENT_NOT_FOUND)

        return commentWriter.update(comment = comment, request = command).token
    }

    @Transactional
    override fun deleteComment(command: CommentCommand) {
        val comment = commentReader.getComment(command.token)
            ?: throw NotFoundException(ErrorCode.COMMENT_NOT_FOUND)

        return commentWriter.delete(comment)
    }
}