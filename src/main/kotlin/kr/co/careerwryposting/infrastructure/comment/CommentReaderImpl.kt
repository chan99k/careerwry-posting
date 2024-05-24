package kr.co.careerwryposting.infrastructure.comment

import kr.co.careerwryposting.domain.comment.Comment
import kr.co.careerwryposting.domain.comment.CommentReader
import org.springframework.stereotype.Repository

@Repository
class CommentReaderImpl(
    private val commentRepository: CommentRepository,
) : CommentReader {

    override fun getComment(token: String): Comment? = commentRepository.findByToken(token)
}