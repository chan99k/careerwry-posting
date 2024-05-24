package kr.co.careerwryposting.application.post

import kr.co.careerwryposting.common.response.SliceResponse
import kr.co.careerwryposting.domain.post.PostCommand
import kr.co.careerwryposting.domain.post.PostService
import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class PostFacade(
    private val postService: PostService,
) {

    fun getAllPostings(pageable: Pageable): SliceResponse<PostDto.PostResponse> {
        val slice = postService.findAll(pageable = pageable)
            .map { PostDto.PostResponse.of(it) }
        return SliceResponse.fromSlice(slice)
    }

    fun getPost(token: String): PostDto.PostResponse {
        return postService.getPost(token)
    }

    // FIXME :: 댓글 정보 페이지블로 바꾸기 -> 이거 프론트에서는 어떻게 처리함?
    //  -> 엔드포인트 리소스 하나에 페이지블 바꿔가면서 계속 보내고 화면은 그대로 두나? 아니면 따로 댓글 목록 조회하는 엔드포인트를 파줘야함?
    //  : 만약 2번이라면 지금처럼 한번에 다 조회하는게 아니라 엔드포인트 여러개를 조합해서 게시글 상세페이지를 만들겠네?
    fun getPostDetails(token: String): PostDto.PostResponse {
        return PostDto.PostResponse.detail(postService.getPostDetails(token))

    }

    fun savePost(command: PostCommand): PostDto.PostResponse {
        return PostDto.PostResponse.of(postService.savePost(command))
    }

    fun updatePost(request: PostDto.PostUpdateRequest) {
        postService.updatePost(request)
    }

    fun deletePost(token: String) {
        postService.deletePost(token)
    }

    fun findPosts(request: PostDto.PostSearchRequest): List<PostDto.PostResponse> {
        return postService.findPosts(request)
    }

}