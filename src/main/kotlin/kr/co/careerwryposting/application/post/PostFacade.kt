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
            .map { PostDto.PostResponse.tiny(it) }
        return SliceResponse.fromSlice(slice)
    }

    fun getPost(token: String): PostDto.PostResponse {
        return PostDto.PostResponse.detail(postService.getPost(token))
    }

    fun savePost(command: PostCommand): PostDto.PostResponse {
        return PostDto.PostResponse.tiny(postService.savePost(command))
    }

    fun updatePost(request: PostDto.PostUpdateRequest) {
        postService.updatePost(request)
    }

    fun deletePost(token: String) {
        postService.deletePost(token)
    }

    fun findPosts(request: PostDto.PostSearchRequest): List<PostDto.PostResponse> {
        return postService.findPosts(request).map { PostDto.PostResponse.tiny(it) }
    }

}