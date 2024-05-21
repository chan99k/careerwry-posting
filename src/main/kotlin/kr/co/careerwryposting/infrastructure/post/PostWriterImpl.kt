package kr.co.careerwryposting.infrastructure.post

import kr.co.careerwryposting.domain.post.Post
import kr.co.careerwryposting.domain.post.PostWriter
import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.stereotype.Component

@Component
class PostWriterImpl(
    private val postRepository: PostRepository,
) : PostWriter {
    override fun save(request: PostDto.PostRequest): Post { // TODO :: Post 리턴을 PostInfo 리턴으로 변경
        return postRepository.save(Post.fixture(request.title, request.content))
    }

    override fun update(post: Post, request: PostDto.PostUpdateRequest) {
        post.updatePost(request)
        postRepository.save(post)
    }

    override fun delete(token: String) {
        postRepository.deleteByToken(token)
    }
}