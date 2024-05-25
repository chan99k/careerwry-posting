package kr.co.careerwryposting.interfaces.comment

import kr.co.careerwryposting.application.comment.CommentFacade
import kr.co.careerwryposting.common.response.CommonResponse
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentApiController(
    private val commentFacade: CommentFacade,
) {
    @PostMapping
    fun addComment(
        @RequestBody commentDto: CommentDto.CommentCreateRequest,
        @RequestParam("post-token") postToken: String,
    ): CommonResponse<*> {
        return commentFacade.addComment(commentDto, postToken)
    }

    @GetMapping
    fun getCommentsByPostToken(
        @RequestParam("post-token") postToken: String,
        pageable: Pageable
    ): CommonResponse<Slice<CommentDto.CommentResponse>> {
        return commentFacade.getCommentsByPostToken(postToken, pageable)
    }

    @PutMapping
    fun updateComment(
        @RequestBody commentDto: CommentDto.CommentUpdateRequest,
        @RequestParam("comment-token") commentToken: String,
    ): CommonResponse<*> {
        return commentFacade.updateComment(commentDto, commentToken)
    }

    @DeleteMapping
    fun deleteComment(
        @RequestParam("comment-token") commentToken: String,
    ): CommonResponse<*> {
        return commentFacade.deleteComment(commentToken)
    }
}