package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice


interface PostReader {
//    fun findAll(): List<Post>
    fun findAll(pageable: Pageable): Slice<Post>
    fun getPost(token: String): Post?
    fun findPosts(request: PostDto.PostSearchRequest): List<Post>
}