package kr.co.careerwryposting.infrastructure.post

import kr.co.careerwryposting.domain.post.Post
import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice


interface PostQuerydslRepository {
    fun findPosts(request: PostDto.PostSearchRequest): List<Post>
    fun findAllByQuerydsl(pageable: Pageable): Slice<Post>
    fun getPostDetails(token: String, pageable: Pageable): Post?
}