package kr.co.careerwryposting.domain.comment

import java.time.LocalDateTime

data class CommentInfo(
    val content: String,
    val nickName: String,
    val positionJob: String?,
    val profileImage: String?,
    val createdAt: LocalDateTime,
    val token: String,
) {
    companion object {

        fun of(comment: Comment): CommentInfo {
            return CommentInfo(
                content = comment.content,
                nickName = comment.profile.nickname!!,
                positionJob = comment.profile.jobPosition,
                profileImage = comment.profile.picture,
                createdAt = comment.createdDate!!,
                token = comment.token,
            )
        }
    }
}