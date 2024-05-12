package kr.co.careerwryposting.infrastructure.post

import kr.co.careerwryposting.domain.post.Post
import kr.co.careerwryposting.interfaces.post.PostDto


interface PostQuerydslRepository {
    fun findPosts(request: PostDto.PostSearchRequest): List<Post>
}