package kr.co.careerwryposting.infrastructure.comment

import kr.co.careerwryposting.domain.comment.Comment
import kr.co.careerwryposting.domain.comment.CommentInfo
import kr.co.careerwryposting.domain.comment.CommentReader
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Repository

@Repository
class CommentReaderImpl(
    private val commentRepository: CommentRepository,
) : CommentReader {

    override fun getComment(token: String): Comment? = commentRepository.findByToken(token)
    override fun getCommentsByPostId(postId: Long, pageable: Pageable): Slice<CommentInfo> {
        return commentRepository.getCommentsByPostId(postId, pageable).map {
            CommentInfo.of(comment = it)
        }
    }
}