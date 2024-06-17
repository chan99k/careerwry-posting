package kr.co.careerwryposting.interfaces.comment

import kr.co.careerwryposting.application.comment.CommentFacade
import kr.co.careerwryposting.common.response.CommonResponse
import kr.co.careerwryposting.common.response.SliceResponse
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentApiController(
    private val commentFacade: CommentFacade
) {
    @PostMapping
    fun addComment(
        @RequestBody commentDto: CommentDto.CommentCreateRequest,
        @RequestParam("post-token") postToken: String
    ): CommonResponse<*> {
        return CommonResponse.success(commentFacade.addComment(commentDto, postToken))
    }

    @GetMapping
    fun getCommentsByPostToken(
        @RequestParam("post-token") postToken: String,
        pageable: Pageable
    ): CommonResponse<SliceResponse<CommentDto.CommentResponse>> {
        return CommonResponse.success(commentFacade.getCommentsByPostToken(postToken, pageable))
    }

    @PutMapping
    fun updateComment(
        @RequestBody commentDto: CommentDto.CommentUpdateRequest,
        @RequestParam("comment-token") commentToken: String
    ): CommonResponse<*> {
        return commentFacade.updateComment(commentDto, commentToken)
    }

    @DeleteMapping
    fun deleteComment(
        @RequestParam("comment-token") commentToken: String
    ): CommonResponse<*> {
        return commentFacade.deleteComment(commentToken)
    }
}
