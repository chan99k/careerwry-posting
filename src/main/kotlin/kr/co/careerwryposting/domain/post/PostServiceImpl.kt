package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.common.exeption.NotFoundException
import kr.co.careerwryposting.common.response.ErrorCode
import kr.co.careerwryposting.interfaces.post.PostDto
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostServiceImpl(
    private val postReader: PostReader,
    private val postWriter: PostWriter
) : PostService {

    @Transactional(readOnly = true)
    override fun findAll(pageable: Pageable): Slice<PostInfo> {
        return postReader.findAll(pageable).map { PostInfo.fromEntity(it) }
    }

    @Transactional(readOnly = true)
    override fun getPost(token: String): PostInfo {
        return postReader.getPost(token)
            ?.let { PostInfo.fromEntity(it) }
            ?: throw NotFoundException(ErrorCode.POST_NOT_FOUND)
    }

    @Transactional
    override fun savePost(command: PostCommand): PostInfo {
        return postWriter.save(command)
    }

    @Transactional
    override fun updatePost(request: PostDto.PostUpdateRequest) {
        postReader.getPost(request.token)
            ?.let { postWriter.update(it, request) }
            ?: throw NotFoundException(ErrorCode.POST_NOT_FOUND)
    }

    @Transactional
    override fun deletePost(token: String) {
        postReader.getPost(token)
            ?.let { postWriter.delete(it.token) }
            ?: throw NotFoundException(ErrorCode.POST_NOT_FOUND)
    }

    @Transactional(readOnly = true)
    override fun findPosts(request: PostDto.PostSearchRequest): List<PostInfo> {
        return postReader.findPosts(request)
            .takeIf { it.isNotEmpty() }
            ?.map { PostInfo.fromEntity(it) }
            ?: throw NotFoundException(ErrorCode.POST_NO_RESULTS)
    }
}
