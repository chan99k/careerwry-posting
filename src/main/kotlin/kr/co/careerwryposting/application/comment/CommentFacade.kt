package kr.co.careerwryposting.application.comment

import kr.co.careerwryposting.common.response.CommonResponse
import kr.co.careerwryposting.common.response.SliceResponse
import kr.co.careerwryposting.domain.comment.CommentCommand
import kr.co.careerwryposting.domain.comment.CommentService
import kr.co.careerwryposting.domain.post.PostService
import kr.co.careerwryposting.interfaces.comment.CommentDto
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CommentFacade(
    private val commentService: CommentService,
    private val postService: PostService,
) {

    fun addComment(request: CommentDto.CommentCreateRequest, postToken: String): CommentDto.CommentTinyResponse {
        return CommentDto.CommentTinyResponse.of(
            commentService.addComment(
                CommentCommand.createCommand(
                    request,
                    postToken
                )
            )
        )
    }

    fun updateComment(request: CommentDto.CommentUpdateRequest, commentToken: String): CommonResponse<*> {
        return CommonResponse.success(commentService.updateComment(CommentCommand.updateCommand(request, commentToken)))
    }

    fun deleteComment(commentToken: String): CommonResponse<*> {
        return CommonResponse.success(commentService.deleteComment(CommentCommand.deleteCommand(commentToken)))
    }

    fun getCommentsByPostToken(
        postToken: String,
        pageable: Pageable
    ): SliceResponse<CommentDto.CommentResponse> {
        val postId = postService.getPost(postToken).id
        val slice = commentService.getCommentsByPostToken(postId, pageable)
            .map { CommentDto.CommentResponse.of(it) }

        return SliceResponse.fromSlice(slice)
    }

}

