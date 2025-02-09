package kr.co.careerwryposting.application.like

import kr.co.careerwryposting.domain.like.LikeCommand
import kr.co.careerwryposting.domain.like.LikeService
import kr.co.careerwryposting.interfaces.like.LikeDto
import org.springframework.stereotype.Service

@Service
class LikeFacade(
    private val likeService: LikeService,
) {
    fun getLikesByPostToken(postToken: String): Long { // postToken 을 먼저 복합키로 걸어야 하네
        return likeService.countLike(postToken)
    }

    fun createLike(likeEvent: LikeDto.LikeEvent) {
        likeService.createLike(LikeCommand.CreateCommand.of(likeEvent))
    }

    fun deleteLike(likeEvent: LikeDto.LikeEvent) {
        likeService.deleteLike(LikeCommand.DeleteCommand.of(likeEvent))
    }
}