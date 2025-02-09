package kr.co.careerwryposting.interfaces.like

import kr.co.careerwryposting.application.like.LikeFacade
import kr.co.careerwryposting.common.response.CommonResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/likes")
class LikeApiController(
    private val likeFacade: LikeFacade,
) {
    @GetMapping("/{postToken}")
    fun getLikesOfCurrentPost(@PathVariable postToken: String): CommonResponse<*> {
        return CommonResponse.success(likeFacade.getLikesByPostToken(postToken))
    }

    @PostMapping("/{postToken}")
    fun createLikeByPostToken(
        @PathVariable postToken: String,
    ) {
        val currUser = SecurityContextHolder.getContext().authentication.name.takeIf { it.isNotEmpty() } ?: "chan99k"
        likeFacade.createLike(LikeDto.LikeEvent(postToken, currUser))
    }

    @DeleteMapping("/{likeToken}")
    fun deleteLikeByToken(
        @PathVariable likeToken: String,
    ) {
        val currUser = SecurityContextHolder.getContext().authentication.name.takeIf { it.isNotEmpty() } ?: "chan99k"
        likeFacade.deleteLike(LikeDto.LikeEvent(likeToken, currUser))
    }
}