package kr.co.careerwryposting.infrastructure.like

import kr.co.careerwryposting.common.exeption.NotFoundException
import kr.co.careerwryposting.common.util.RedisUtil
import kr.co.careerwryposting.domain.like.Like
import kr.co.careerwryposting.domain.like.LikeCommand
import kr.co.careerwryposting.domain.like.LikeWriter
import kr.co.careerwryposting.infrastructure.post.PostRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Repository

@Repository
class LikeWriterImpl(
    private val likeRepository: LikeRepository,
    private val postRepository: PostRepository,
    private val redisUtil: RedisUtil,
) : LikeWriter {

    override fun save(command: LikeCommand.CreateCommand) {
        val post = postRepository.findByToken(command.postToken) ?: throw NotFoundException("해당 포스트가 존재하지 않습니다.")
        val like = Like.fixture(post, command.createdBy) // 사용자 정보를 함께 보내지 않으면 엉뚱한 사람의 좋아요가 눌릴 수도...?
        likeRepository.save(like)
        redisUtil.increment(redisUtil.getLikeCountKey(command.postToken))
    }


    override fun delete(command: LikeCommand.DeleteCommand) {
        val post = postRepository.findByToken(command.postToken) ?: throw NotFoundException("해당 포스트가 존재하지 않습니다.")
        val authentication = SecurityContextHolder.getContext().authentication
        val currUser = authentication?.name ?: "anonymousUser"
        val like =
            likeRepository.findLikeByPostIdAndCreatedBy(
                post.id!!,
                currUser,
            )
                ?: throw NotFoundException("해당 좋아요가 존재하지 않습니다")
        likeRepository.deleteById(like.id!!) // like 가 있으면 아이디는 무조건 존재한다.
        redisUtil.decrement(redisUtil.getLikeCountKey(command.postToken))
    }

}