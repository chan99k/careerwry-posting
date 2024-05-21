package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.interfaces.post.PostDto


interface PostReader {
    fun findAll(): List<Post>
    fun getPost(token: String): Post?
    fun findPosts(request: PostDto.PostSearchRequest): List<Post>
}