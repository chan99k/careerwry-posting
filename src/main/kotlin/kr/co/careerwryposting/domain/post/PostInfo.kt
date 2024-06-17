package kr.co.careerwryposting.domain.post

import kr.co.careerwryposting.domain.comment.Comment
import java.time.LocalDateTime

data class PostInfo(
    val title: String,
    val content: String,
    val viewCount: Long,
    val nickName: String,
    val picture: String,
    val jobPosition: String?,
    val idProvider: String,
    val userId: String,
    val lastModifiedDate: LocalDateTime?,
    val lastModifiedBy: String?,
    val createdAt: LocalDateTime?,
    val createdBy: String?,

    val comments: MutableList<Comment>,
    val token: String,
    val id: Long
) {
    companion object {
        fun fromEntity(post: Post): PostInfo {
            return PostInfo(
                title = post.title,
                content = post.content,
                viewCount = post.viewCount,
                nickName = post.profile.nickname,
                picture = post.profile.picture,
                jobPosition = post.profile.jobPosition,
                idProvider = post.profile.idProvider,
                userId = post.profile.userId,
                lastModifiedDate = post.lastModifiedDate,
                lastModifiedBy = post.lastModifiedBy,
                createdAt = post.createdDate,
                createdBy = post.createdBy,

                comments = post.comments,
                token = post.token,
                id = post.id!!
            )
        }
    }
}
