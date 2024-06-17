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
@RequestMapping("/posts")
class PostApiController(
    private val postFacade: PostFacade
) {
    @GetMapping
    fun getAllPostings(pageable: Pageable): CommonResponse<SliceResponse<PostDto.PostResponse>> {
        return CommonResponse.success(postFacade.getAllPostings(pageable)) // FIXME ::  댓글의 수, 혹은 아이디만 반환하도록
    }

    // TODO :: 로그인 한 사용자만 작성할 수 있도록 변경하기
    @PostMapping
    fun savePost(
        @RequestBody @Valid
        request: PostDto.PostRequest
    ): CommonResponse<PostDto.PostResponse> {
        return CommonResponse.success(postFacade.savePost(PostCommand.of(request)), HttpStatus.ACCEPTED)
    }

    @GetMapping("/{token}")
    fun getPostDetails(@PathVariable token: String): CommonResponse<PostDto.PostResponse> {
        return CommonResponse.success(postFacade.getPost(token))
    }

    // TODO :: 게시글 검색 기능 페이징-동적정렬 로 리팩터링 하기
    @GetMapping("/search")
    fun findPosts(@RequestBody request: PostDto.PostSearchRequest): CommonResponse<List<PostDto.PostResponse>> {
        return CommonResponse.success(postFacade.findPosts(request), HttpStatus.FOUND)
    }

    // TODO :: 게시글 주인만 변경할 수 있도록 리팩터링하기
    @PutMapping
    fun updatePost(@RequestBody request: PostDto.PostUpdateRequest): CommonResponse<Any?> {
        postFacade.updatePost(request)
        return CommonResponse.success(HttpStatus.ACCEPTED)
    }

    // TODO :: 게시글 주인과 관리자만 삭제할 수 있도록 리팩터링 하기
    @DeleteMapping("/{token}")
    fun deletePost(@PathVariable token: String): CommonResponse<Any?> {
        postFacade.deletePost(token)
        return CommonResponse.success(HttpStatus.ACCEPTED)
    }
}
