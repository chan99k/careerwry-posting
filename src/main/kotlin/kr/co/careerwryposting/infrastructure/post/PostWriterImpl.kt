package kr.co.careerwryposting.infrastructure.post

import kr.co.careerwryposting.domain.post.Post
import kr.co.careerwryposting.domain.post.PostCommand
import kr.co.careerwryposting.domain.post.PostInfo
import kr.co.careerwryposting.domain.post.PostWriter
import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.stereotype.Component

@Component
class PostWriterImpl(
    private val postRepository: PostRepository
) : PostWriter {
    override fun save(command: PostCommand): PostInfo {
        return PostInfo.fromEntity(postRepository.save(Post.fixture(command.title, command.content)))
    }

    override fun update(post: Post, request: PostDto.PostUpdateRequest) {
        post.updatePost(PostCommand.ofUpdate(request))
        postRepository.save(post)
    }

    override fun delete(token: String) {
        postRepository.deleteByToken(token)
    }
}
