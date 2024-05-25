package kr.co.careerwryposting.domain.comment

import java.time.LocalDateTime

data class CommentInfo(
    val content: String,
    val nickName: String,
    val picture: String,
    val jobPosition: String?,
    val idProvider: String,
    val userId: String,
    val createdDate: LocalDateTime,
    val lastModifiedDate: LocalDateTime?,
    val createdBy: String? = null,
    val lastModifiedBy: String?,
    val token: String,
) {
    companion object {

        fun of(comment: Comment): CommentInfo {
            return CommentInfo(
                content = comment.content,
                nickName = comment.profile.nickname,
                picture = comment.profile.picture,
                jobPosition = comment.profile.jobPosition,
                idProvider = comment.profile.idProvider,
                userId = comment.profile.userId,
                createdDate = comment.createdDate!!,
                lastModifiedDate = comment.lastModifiedDate,
                createdBy = comment.createdBy,
                lastModifiedBy = comment.lastModifiedBy,
                token = comment.token,
            )
        }
    }
}