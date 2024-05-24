package kr.co.careerwryposting.interfaces.comment

import kr.co.careerwryposting.application.comment.CommentFacade
import kr.co.careerwryposting.common.response.CommonResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentApiController(
    private val commentFacade: CommentFacade,
) {
    @PostMapping("/{post_token}")
    fun addComment(
        @RequestBody commentDto: CommentDto.CommentCreateRequest,
        @PathVariable("post_token") postToken: String,
    ): CommonResponse<*> {
        return commentFacade.addComment(commentDto, postToken)
    }

    @PutMapping("/{comment_token}")
    fun updateComment(
        @RequestBody commentDto: CommentDto.CommentUpdateRequest,
        @PathVariable("comment_token") commentToken: String,
    ): CommonResponse<*> {
        return commentFacade.updateComment(commentDto, commentToken)
    }

    @DeleteMapping("/{comment_token}")
    fun deleteComment(
        @PathVariable("comment_token") commentToken: String,
    ): CommonResponse<*> {
        return commentFacade.deleteComment(commentToken)
    }
}