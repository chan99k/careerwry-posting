package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.interfaces.post.PostDto


interface PostService {
    fun findAll(): List<PostDto.PostResponse>

    fun getPost(token: String): PostDto.PostResponse

    fun savePost(request: PostDto.PostRequest): Post

    fun updatePost(request: PostDto.PostUpdateRequest)

    fun deletePost(token: String)

    fun findPosts(request: PostDto.PostSearchRequest): List<PostDto.PostResponse>
}

