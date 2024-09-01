package kr.co.careerwryposting.infrastructure.like

import kr.co.careerwryposting.common.exeption.NotFoundException
import kr.co.careerwryposting.domain.like.LikeReader
import kr.co.careerwryposting.infrastructure.post.PostRepository
import org.springframework.stereotype.Repository

@Repository
class LikeReaderImpl(
    private val likeRepository: LikeRepository,
    private val postRepository: PostRepository,
) : LikeReader {
    override fun countByPostToken(postToken: String): Long {
        val post = postRepository.findByToken(postToken) ?: throw NotFoundException("해당 포스트가 존재하지 않습니다.")
        return likeRepository.countLikesByPostId(post.id!!)
    }
}