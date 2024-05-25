package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice


interface PostService {
//    fun findAll(): List<PostInfo>

    fun findAll(pageable: Pageable): Slice<PostInfo>

    fun getPost(token: String): PostInfo

    fun savePost(command: PostCommand): PostInfo

    fun updatePost(request: PostDto.PostUpdateRequest)

    fun deletePost(token: String)

    fun findPosts(request: PostDto.PostSearchRequest): List<PostInfo>
}

