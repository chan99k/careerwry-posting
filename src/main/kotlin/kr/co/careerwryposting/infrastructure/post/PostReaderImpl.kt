package kr.co.careerwryposting.infrastructure.post

import kr.co.careerwryposting.domain.post.Post
import kr.co.careerwryposting.domain.post.PostReader
import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.stereotype.Component

@Component
class PostReaderImpl(
    private val postRepository: PostRepository,
) : PostReader {
    override fun getPost(token: String): Post = postRepository.findByToken(token)

    override fun findPosts(request: PostDto.PostSearchRequest): List<Post> {
        return postRepository.findPosts(request)
    }

    override fun findAll(): List<Post> = postRepository.findAll()
}