package kr.co.careerwryposting.interfaces.post

import jakarta.validation.Valid
import kr.co.careerwryposting.application.post.PostFacade
import kr.co.careerwryposting.common.response.CommonResponse
import kr.co.careerwryposting.common.response.SliceResponse
import kr.co.careerwryposting.domain.post.PostCommand
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/postings")
class PostApiController(
    private val postFacade: PostFacade,
) {
    @GetMapping
    fun getAllPostings(pageable: Pageable): CommonResponse<SliceResponse<PostDto.PostResponse>> {
        return CommonResponse.success(postFacade.getAllPostings(pageable))
    }

    @PostMapping
    fun savePost(@RequestBody @Valid request: PostDto.PostRequest): CommonResponse<PostDto.PostResponse> {
        return CommonResponse.success(postFacade.savePost(PostCommand.of(request)), HttpStatus.ACCEPTED)
    }

    @GetMapping("/{token}")
    fun getPost(@PathVariable token: String): CommonResponse<PostDto.PostResponse> {
        return CommonResponse.success(postFacade.getPost(token))
    }

    @GetMapping("/search")
    fun findPosts(@RequestBody request: PostDto.PostSearchRequest): CommonResponse<List<PostDto.PostResponse>> {
        return CommonResponse.success(postFacade.findPosts(request), HttpStatus.FOUND)
    }

    @PutMapping
    fun updatePost(@RequestBody request: PostDto.PostUpdateRequest): CommonResponse<Any?> {
        postFacade.updatePost(request)
        return CommonResponse.success(HttpStatus.ACCEPTED)
    }

    @DeleteMapping("/{token}")
    fun deletePost(@PathVariable token: String): CommonResponse<Any?> {
        postFacade.deletePost(token)
        return CommonResponse.success(HttpStatus.ACCEPTED)
    }


}
