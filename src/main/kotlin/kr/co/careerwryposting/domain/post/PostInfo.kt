package kr.co.careerwryposting.domain.post

import java.time.LocalDateTime

data class PostInfo(
    val title: String,
    val content: String,
    val nickName: String,
    val positionJob: String?,
    val profileImage: String?,
    val createdAt: LocalDateTime,
    val token: String,
) {
    companion object {

        fun of(post: Post): PostInfo {
            return PostInfo(
                title = post.title,
                content = post.content,
                nickName = post.profile.nickname,
                positionJob = post.profile.positionJob,
                profileImage = post.profile.profileImage,
                createdAt = post.createdDate!!,
                token = post.token,
            )
        }
    }
}