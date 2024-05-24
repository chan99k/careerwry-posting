package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.domain.comment.Comment
import java.time.LocalDateTime


data class PostInfo(
    val title: String,
    val content: String,
    val nickName: String,
    val positionJob: String?,
    val profileImage: String?,
    val comments: MutableList<Comment>,
    val createdAt: LocalDateTime,
    val token: String,
) {
    companion object {

        fun of(post: Post): PostInfo {
            return PostInfo(
                title = post.title,
                content = post.content,
                nickName = post.profile.nickname!!,
                positionJob = post.profile.jobPosition,
                profileImage = post.profile.picture,
                comments = post.comments,
                createdAt = post.createdDate!!,
                token = post.token,
            )
        }
    }
}