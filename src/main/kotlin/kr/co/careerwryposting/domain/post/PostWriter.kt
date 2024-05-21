package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.interfaces.post.PostDto

interface PostWriter {
    fun save(request: PostDto.PostRequest): Post
    fun update(post: Post, request: PostDto.PostUpdateRequest)
    fun delete(token: String)
}