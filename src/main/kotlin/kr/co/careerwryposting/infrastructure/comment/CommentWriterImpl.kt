package kr.co.careerwryposting.infrastructure.comment

import kr.co.careerwryposting.common.exeption.NotFoundException
import kr.co.careerwryposting.common.response.ErrorCode
import kr.co.careerwryposting.domain.UserProfile
import kr.co.careerwryposting.domain.comment.*
import kr.co.careerwryposting.domain.post.PostReader
import org.springframework.stereotype.Component

@Component
class CommentWriterImpl(
    private val commentRepository: CommentRepository,
    private val postReader: PostReader
) : CommentWriter {

    override fun save(command: CommentCommand): CommentInfo {
        val post = postReader.getPost(command.token)
            ?: throw NotFoundException(ErrorCode.POST_NOT_FOUND)

        val comment = Comment(
            content = command.content!!,
            profile = UserProfile(
                "JordyTest",
                "URL",
                "백엔드",
                "https://kauth.kakao.com",
                "10239012"
            ),
            post = post
        )
        post.addComment()
        return CommentInfo.of(commentRepository.save(comment))
    }

    override fun update(comment: Comment, request: CommentCommand): CommentInfo {
        comment.updateComment(request.content!!)
        return CommentInfo.of(commentRepository.save(comment))
    }

    override fun delete(comment: Comment) {
        comment.post!!.deleteComment()
        commentRepository.delete(comment)
    }
}
