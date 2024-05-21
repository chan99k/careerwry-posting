package kr.co.careerwryposting.application.post

import kr.co.careerwryposting.domain.post.PostService
import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.stereotype.Service

@Service
class PostFacade(
    private val postService: PostService,
) {
    fun getAllPostings(): List<PostDto.PostResponse> {
        return postService.findAll()
    }

    fun getPost(token: String): PostDto.PostResponse {
        return postService.getPost(token)
    }

    fun savePost(request: PostDto.PostRequest): PostDto.PostResponse {
        return PostDto.PostResponse.of(postService.savePost(request))
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