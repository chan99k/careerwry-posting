package kr.co.careerwryposting.domain.like

import jakarta.transaction.Transactional
import kr.co.careerwryposting.common.util.RedisUtil
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class LikeServiceImpl(
    private val eventPublisher: ApplicationEventPublisher,
    private val redisUtil: RedisUtil,
    private val likeReader: LikeReader,
) : LikeService {
    @Transactional
    override fun createLike(likeCommand: LikeCommand.CreateCommand) {
        eventPublisher.publishEvent(likeCommand)
    }

    @Transactional
    override fun deleteLike(likeCommand: LikeCommand.DeleteCommand) {
        eventPublisher.publishEvent(likeCommand)
    }
    // TODO : 로직 수정 필요함
    override fun countLike(postToken: String): Long {
        redisUtil.getCount(redisUtil.getLikeCountKey(postToken))?.let { return it }
        with(likeReader.countByPostToken(postToken)) {
            redisUtil.setData(redisUtil.getLikeCountKey(postToken), this)
            return this
        }
    }

}





