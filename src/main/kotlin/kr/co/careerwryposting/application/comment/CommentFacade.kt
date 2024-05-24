package kr.co.careerwryposting.application.comment

import kr.co.careerwryposting.common.response.CommonResponse
import kr.co.careerwryposting.domain.comment.CommentCommand
import kr.co.careerwryposting.domain.comment.CommentService
import kr.co.careerwryposting.interfaces.comment.CommentDto
import org.springframework.stereotype.Service

@Service
class CommentFacade(
    private val commentService: CommentService,
) {

    fun addComment(request: CommentDto.CommentCreateRequest, postToken: String): CommonResponse<*> {
        return CommonResponse.success(commentService.addComment(CommentCommand.createCommand(request, postToken)))
    }

    fun updateComment(request: CommentDto.CommentUpdateRequest, commentToken: String): CommonResponse<*> {
        return CommonResponse.success(commentService.updateComment(CommentCommand.updateCommand(request, commentToken)))
    }

    fun deleteComment(commentToken: String): CommonResponse<*> {
        return CommonResponse.success(commentService.deleteComment(CommentCommand.deleteCommand(commentToken)))
    }

}

