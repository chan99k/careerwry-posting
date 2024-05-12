package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val postReader: PostReader,
    private val postWriter: PostWriter,
) : PostService {

    @Transactional(readOnly = true)
    override fun findAll(): List<PostDto.PostResponse> {
        return postReader.findAll().map { PostDto.PostResponse.of(it) }
    }

    @Transactional(readOnly = true)
    override fun getPost(token: String): PostDto.PostResponse {
        return PostDto.PostResponse.of(postReader.getPost(token))
    }

    @Transactional
    override fun savePost(request: PostDto.PostRequest) {
        postWriter.save(request)
    }

    @Transactional
    override fun updatePost(request: PostDto.PostUpdateRequest) {
        val targetPost = postReader.getPost(request.token)
        postWriter.update(targetPost, request)
    }

    @Transactional
    override fun deletePost(token: String) {
        postWriter.delete(token)
    }

    @Transactional(readOnly = true)
    override fun findPosts(request: PostDto.PostSearchRequest): List<PostDto.PostResponse> {
        return postReader.findPosts(request).map { PostDto.PostResponse.of(it) }
    }
}